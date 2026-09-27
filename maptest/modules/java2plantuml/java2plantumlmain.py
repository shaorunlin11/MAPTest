import os
from .javaanalyzer import JavaAnalyzer
from .uml_pruner import UMLPruner, GlobalIndexer

def parser(path_to_java_files, focal_class: str = None, focal_method: str = None, focal_method_code: str = None, depth_limit: int = 3) -> str:
    """
    Get an analyzer for a project or file, then optionally prune for a focal method.
    """
    # 1. Index the project using the new GlobalIndexer (tree-sitter based)
    indexer = GlobalIndexer()
    
    repo_path = path_to_java_files
    if os.path.isfile(path_to_java_files):
        # If it's a single file, we might want to index its parent directory or the src/main/java root if possible
        # For now, just index the file's directory as a fallback
        repo_path = os.path.dirname(path_to_java_files)
        indexer.index_file(path_to_java_files)
    else:
        indexer.index_project(repo_path)

    # 2. If pruning is requested
    if focal_class and focal_method and focal_method_code:
        pruner = UMLPruner(indexer)
        plantuml_code = pruner.prune(focal_class, focal_method_code, depth_limit=depth_limit)
        if plantuml_code:
            return plantuml_code

    # 3. Fallback to the old JavaAnalyzer (javalang based) for full UML if pruning not requested or failed
    analyzer = JavaAnalyzer()
    if os.path.isfile(path_to_java_files):
        analyzer.analyze_file(path_to_java_files)
    else:
        for root, dirs, files in os.walk(path_to_java_files):
            for file in files:
                if file.endswith(".java"):
                    analyzer.analyze_file(str(os.path.join(root, file)))

    analyzer.analyze_relations()
    plantuml_code = "@startuml\n" + analyzer.pakcages_plantuml + analyzer.relations_plantuml + "@enduml"
    return plantuml_code


if __name__ == "__main__":
    import argparse
    cli = argparse.ArgumentParser(description="Render Java source as PlantUML text.")
    cli.add_argument("source", help="Java file or source directory")
    plantuml = parser(cli.parse_args().source)
    print(plantuml)
