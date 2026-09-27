"""Compatibility launcher; prefer python run.py --stage initial --project ID."""
import sys
from pathlib import Path

ROOT = Path(__file__).resolve().parents[2]
sys.path.insert(0, str(ROOT))

from run import main

if __name__ == '__main__':
    raise SystemExit(main(['--stage', 'initial', *sys.argv[1:]]))
