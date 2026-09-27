package com.fasterxml.jackson.core.json;

import org.junit.Test;
import org.junit.Assert;
import com.fasterxml.jackson.core.format.InputAccessor;
import com.fasterxml.jackson.core.format.MatchStrength;

import java.io.IOException;

public class ByteSourceJsonBootstrapperhasJSONFormatTest {

    @Test
    public void testHasJSONFormatWithEmptyInput() throws IOException {
        InputAccessor mockAcc = new InputAccessor() {
            @Override
            public boolean hasMoreBytes() throws IOException {
                return false;
            }

            @Override
            public byte nextByte() throws IOException {
                throw new IOException("No more bytes");
            }

            @Override
            public void reset() {
                // No-op
            }
        };

        MatchStrength result = ByteSourceJsonBootstrapper.hasJSONFormat(mockAcc);
        Assert.assertEquals(MatchStrength.INCONCLUSIVE, result);
    }

    @Test
    public void testHasJSONFormatWithUTF8BOM() throws IOException {
        InputAccessor mockAcc = new InputAccessor() {
            private int index = 0;
            private final byte[] data = { (byte) 0xEF, (byte) 0xBB, (byte) 0xBF, ' ', '{', '}' };

            @Override
            public boolean hasMoreBytes() throws IOException {
                return index < data.length;
            }

            @Override
            public byte nextByte() throws IOException {
                if (index >= data.length) {
                    throw new IOException("No more bytes");
                }
                return data[index++];
            }

            @Override
            public void reset() {
                // No-op
            }
        };

        MatchStrength result = ByteSourceJsonBootstrapper.hasJSONFormat(mockAcc);
        Assert.assertEquals(MatchStrength.SOLID_MATCH, result);
    }

    @Test
    public void testHasJSONFormatWithInvalidUTF8BOM() throws IOException {
        InputAccessor mockAcc = new InputAccessor() {
            private int index = 0;
            private final byte[] data = { (byte) 0xEF, (byte) 0xBB, (byte) 0xBE };

            @Override
            public boolean hasMoreBytes() throws IOException {
                return index < data.length;
            }

            @Override
            public byte nextByte() throws IOException {
                if (index >= data.length) {
                    throw new IOException("No more bytes");
                }
                return data[index++];
            }

            @Override
            public void reset() {
                // No-op
            }
        };

        MatchStrength result = ByteSourceJsonBootstrapper.hasJSONFormat(mockAcc);
        Assert.assertEquals(MatchStrength.NO_MATCH, result);
    }

    @Test
    public void testHasJSONFormatWithJsonObject() throws IOException {
        InputAccessor mockAcc = new InputAccessor() {
            private int index = 0;
            private final byte[] data = { ' ', '{', '}' };

            @Override
            public boolean hasMoreBytes() throws IOException {
                return index < data.length;
            }

            @Override
            public byte nextByte() throws IOException {
                if (index >= data.length) {
                    throw new IOException("No more bytes");
                }
                return data[index++];
            }

            @Override
            public void reset() {
                // No-op
            }
        };

        MatchStrength result = ByteSourceJsonBootstrapper.hasJSONFormat(mockAcc);
        Assert.assertEquals(MatchStrength.SOLID_MATCH, result);
    }

    @Test
    public void testHasJSONFormatWithJsonArray() throws IOException {
        InputAccessor mockAcc = new InputAccessor() {
            private int index = 0;
            private final byte[] data = { ' ', '[', ']' };

            @Override
            public boolean hasMoreBytes() throws IOException {
                return index < data.length;
            }

            @Override
            public byte nextByte() throws IOException {
                if (index >= data.length) {
                    throw new IOException("No more bytes");
                }
                return data[index++];
            }

            @Override
            public void reset() {
                // No-op
            }
        };

        MatchStrength result = ByteSourceJsonBootstrapper.hasJSONFormat(mockAcc);
        Assert.assertEquals(MatchStrength.SOLID_MATCH, result);
    }

    @Test
    public void testHasJSONFormatWithString() throws IOException {
        InputAccessor mockAcc = new InputAccessor() {
            private int index = 0;
            private final byte[] data = { ' ', '"' };

            @Override
            public boolean hasMoreBytes() throws IOException {
                return index < data.length;
            }

            @Override
            public byte nextByte() throws IOException {
                if (index >= data.length) {
                    throw new IOException("No more bytes");
                }
                return data[index++];
            }

            @Override
            public void reset() {
                // No-op
            }
        };

        MatchStrength result = ByteSourceJsonBootstrapper.hasJSONFormat(mockAcc);
        Assert.assertEquals(MatchStrength.WEAK_MATCH, result);
    }

    @Test
    public void testHasJSONFormatWithNumber() throws IOException {
        InputAccessor mockAcc = new InputAccessor() {
            private int index = 0;
            private final byte[] data = { ' ', '1' };

            @Override
            public boolean hasMoreBytes() throws IOException {
                return index < data.length;
            }

            @Override
            public byte nextByte() throws IOException {
                if (index >= data.length) {
                    throw new IOException("No more bytes");
                }
                return data[index++];
            }

            @Override
            public void reset() {
                // No-op
            }
        };

        MatchStrength result = ByteSourceJsonBootstrapper.hasJSONFormat(mockAcc);
        Assert.assertEquals(MatchStrength.WEAK_MATCH, result);
    }

    @Test
    public void testHasJSONFormatWithNegativeNumber() throws IOException {
        InputAccessor mockAcc = new InputAccessor() {
            private int index = 0;
            private final byte[] data = { ' ', '-', '5' };

            @Override
            public boolean hasMoreBytes() throws IOException {
                return index < data.length;
            }

            @Override
            public byte nextByte() throws IOException {
                if (index >= data.length) {
                    throw new IOException("No more bytes");
                }
                return data[index++];
            }

            @Override
            public void reset() {
                // No-op
            }
        };

        MatchStrength result = ByteSourceJsonBootstrapper.hasJSONFormat(mockAcc);
        Assert.assertEquals(MatchStrength.WEAK_MATCH, result);
    }

    @Test
    public void testHasJSONFormatWithNull() throws IOException {
        InputAccessor mockAcc = new InputAccessor() {
            private int index = 0;
            private final byte[] data = { ' ', 'n', 'u', 'l', 'l' };

            @Override
            public boolean hasMoreBytes() throws IOException {
                return index < data.length;
            }

            @Override
            public byte nextByte() throws IOException {
                if (index >= data.length) {
                    throw new IOException("No more bytes");
                }
                return data[index++];
            }

            @Override
            public void reset() {
                // No-op
            }
        };

        MatchStrength result = ByteSourceJsonBootstrapper.hasJSONFormat(mockAcc);
        Assert.assertEquals(MatchStrength.WEAK_MATCH, result);
    }

    @Test
    public void testHasJSONFormatWithTrue() throws IOException {
        InputAccessor mockAcc = new InputAccessor() {
            private int index = 0;
            private final byte[] data = { ' ', 't', 'r', 'u', 'e' };

            @Override
            public boolean hasMoreBytes() throws IOException {
                return index < data.length;
            }

            @Override
            public byte nextByte() throws IOException {
                if (index >= data.length) {
                    throw new IOException("No more bytes");
                }
                return data[index++];
            }

            @Override
            public void reset() {
                // No-op
            }
        };

        MatchStrength result = ByteSourceJsonBootstrapper.hasJSONFormat(mockAcc);
        Assert.assertEquals(MatchStrength.WEAK_MATCH, result);
    }

    @Test
    public void testHasJSONFormatWithFalse() throws IOException {
        InputAccessor mockAcc = new InputAccessor() {
            private int index = 0;
            private final byte[] data = { ' ', 'f', 'a', 'l', 's', 'e' };

            @Override
            public boolean hasMoreBytes() throws IOException {
                return index < data.length;
            }

            @Override
            public byte nextByte() throws IOException {
                if (index >= data.length) {
                    throw new IOException("No more bytes");
                }
                return data[index++];
            }

            @Override
            public void reset() {
                // No-op
            }
        };

        MatchStrength result = ByteSourceJsonBootstrapper.hasJSONFormat(mockAcc);
        Assert.assertEquals(MatchStrength.WEAK_MATCH, result);
    }

    @Test
    public void testHasJSONFormatWithIncompleteInput() throws IOException {
        InputAccessor mockAcc = new InputAccessor() {
            private int index = 0;
            private final byte[] data = { ' ', '{' };

            @Override
            public boolean hasMoreBytes() throws IOException {
                return index < data.length;
            }

            @Override
            public byte nextByte() throws IOException {
                if (index >= data.length) {
                    throw new IOException("No more bytes");
                }
                return data[index++];
            }

            @Override
            public void reset() {
                // No-op
            }
        };

        MatchStrength result = ByteSourceJsonBootstrapper.hasJSONFormat(mockAcc);
        Assert.assertEquals(MatchStrength.INCONCLUSIVE, result);
    }

    @Test
    public void testHasJSONFormatWithInvalidFormat() throws IOException {
        InputAccessor mockAcc = new InputAccessor() {
            private int index = 0;
            private final byte[] data = { 'A' };

            @Override
            public boolean hasMoreBytes() throws IOException {
                return index < data.length;
            }

            @Override
            public byte nextByte() throws IOException {
                if (index >= data.length) {
                    throw new IOException("No more bytes");
                }
                return data[index++];
            }

            @Override
            public void reset() {
                // No-op
            }
        };

        MatchStrength result = ByteSourceJsonBootstrapper.hasJSONFormat(mockAcc);
        Assert.assertEquals(MatchStrength.NO_MATCH, result);
    }

@Test
    public void testHasJSONFormatWithUTF8BOMAndCorrectNextBytes() throws IOException {
        InputAccessor mockAcc = new InputAccessor() {
            private int index = 0;
            private final byte[] data = { (byte) 0xEF, (byte) 0xBB, (byte) 0xBF, ' ', '{', '}' };

            @Override
            public boolean hasMoreBytes() throws IOException {
                return index < data.length;
            }

            @Override
            public byte nextByte() throws IOException {
                if (index >= data.length) {
                    throw new IOException("No more bytes");
                }
                return data[index++];
            }

            @Override
            public void reset() {
                // No-op
            }
        };

        MatchStrength result = ByteSourceJsonBootstrapper.hasJSONFormat(mockAcc);
        Assert.assertEquals(MatchStrength.SOLID_MATCH, result);
    }

@Test
    public void testHasJSONFormatWithUTF8BOMAndIncorrectNextBytes() throws IOException {
        InputAccessor mockAcc = new InputAccessor() {
            private int index = 0;
            private final byte[] data = { (byte) 0xEF, (byte) 0xBB, (byte) 0xBE };

            @Override
            public boolean hasMoreBytes() throws IOException {
                return index < data.length;
            }

            @Override
            public byte nextByte() throws IOException {
                if (index >= data.length) {
                    throw new IOException("No more bytes");
                }
                return data[index++];
            }

            @Override
            public void reset() {
                // No-op
            }
        };

        MatchStrength result = ByteSourceJsonBootstrapper.hasJSONFormat(mockAcc);
        Assert.assertEquals(MatchStrength.NO_MATCH, result);
    }

@Test
    public void testHasJSONFormatWithUTF8BOMAndMissingNextBytes() throws IOException {
        InputAccessor mockAcc = new InputAccessor() {
            private int index = 0;
            private final byte[] data = { (byte) 0xEF, (byte) 0xBB, (byte) 0xBF };

            @Override
            public boolean hasMoreBytes() throws IOException {
                return index < data.length;
            }

            @Override
            public byte nextByte() throws IOException {
                if (index >= data.length) {
                    throw new IOException("No more bytes");
                }
                return data[index++];
            }

            @Override
            public void reset() {
                // No-op
            }
        };

        MatchStrength result = ByteSourceJsonBootstrapper.hasJSONFormat(mockAcc);
        Assert.assertEquals(MatchStrength.INCONCLUSIVE, result);
    }

@Test
    public void testHasJSONFormatWithUTF8BOMAndCorrectSequence() throws IOException {
        InputAccessor mockAcc = new InputAccessor() {
            private int index = 0;
            private final byte[] data = { (byte) 0xEF, (byte) 0xBB, (byte) 0xBF, ' ', '{', '}' };

            @Override
            public boolean hasMoreBytes() throws IOException {
                return index < data.length;
            }

            @Override
            public byte nextByte() throws IOException {
                if (index >= data.length) {
                    throw new IOException("No more bytes");
                }
                return data[index++];
            }

            @Override
            public void reset() {
                // No-op
            }
        };

        MatchStrength result = ByteSourceJsonBootstrapper.hasJSONFormat(mockAcc);
        Assert.assertEquals(MatchStrength.SOLID_MATCH, result);
    }

@Test
    public void testHasJSONFormatWithUTF8BOMAndIncorrectSecondByte() throws IOException {
        InputAccessor mockAcc = new InputAccessor() {
            private int index = 0;
            private final byte[] data = { (byte) 0xEF, (byte) 0xBB, (byte) 0xBE };

            @Override
            public boolean hasMoreBytes() throws IOException {
                return index < data.length;
            }

            @Override
            public byte nextByte() throws IOException {
                if (index >= data.length) {
                    throw new IOException("No more bytes");
                }
                return data[index++];
            }

            @Override
            public void reset() {
                // No-op
            }
        };

        MatchStrength result = ByteSourceJsonBootstrapper.hasJSONFormat(mockAcc);
        Assert.assertEquals(MatchStrength.NO_MATCH, result);
    }

@Test
    public void testHasJSONFormatWithUTF8BOMAndMissingThirdByte() throws IOException {
        InputAccessor mockAcc = new InputAccessor() {
            private int index = 0;
            private final byte[] data = { (byte) 0xEF, (byte) 0xBB, (byte) 0xBF };

            @Override
            public boolean hasMoreBytes() throws IOException {
                return index < data.length;
            }

            @Override
            public byte nextByte() throws IOException {
                if (index >= data.length) {
                    throw new IOException("No more bytes");
                }
                return data[index++];
            }

            @Override
            public void reset() {
                // No-op
            }
        };

        MatchStrength result = ByteSourceJsonBootstrapper.hasJSONFormat(mockAcc);
        Assert.assertEquals(MatchStrength.INCONCLUSIVE, result);
    }

@Test
    public void testHasJSONFormatWithUTF8BOMAndCorrectSequenceForTargetLine285() throws IOException {
        InputAccessor mockAcc = new InputAccessor() {
            private int index = 0;
            private final byte[] data = { (byte) 0xEF, (byte) 0xBB, (byte) 0xBF, ' ', '{', '}' };

            @Override
            public boolean hasMoreBytes() throws IOException {
                return index < data.length;
            }

            @Override
            public byte nextByte() throws IOException {
                if (index >= data.length) {
                    throw new IOException("No more bytes");
                }
                return data[index++];
            }

            @Override
            public void reset() {
                // No-op
            }
        };

        MatchStrength result = ByteSourceJsonBootstrapper.hasJSONFormat(mockAcc);
        Assert.assertEquals(MatchStrength.SOLID_MATCH, result);
    }
}
