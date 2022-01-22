package data;

public class PentominoesDatabase {
		
    public static boolean[][][][] tPentBool = { // T pieces
		{{{true}, {false}, {false}}, {{true}, {true}, {true}}, {{true}, {false}, {false}}},
		{{{true}, {true}, {true}}, {{false}, {true}, {false}}, {{false}, {true}, {false}}},
		{{{false}, {false}, {true}}, {{true}, {true}, {true}}, {{false}, {false}, {true}}},
		{{{false}, {true}, {false}}, {{false}, {true}, {false}}, {{true}, {true}, {true}}},
		{{{true, false, false}}, {{true, true, true}}, {{true, false, false}}},
		{{{false, false, true}}, {{true, true, true}}, {{false, false, true}}},
		{{{false, true, false}}, {{false, true, false}}, {{true, true, true}}},
		{{{true, true, true}}, {{false, true, false}}, {{false, true, false}}},
		{{{true, true, true}, {false, true, false}, {false, true, false}}},
		{{{true, false, false}, {true, true, true}, {true, false, false}}},
		{{{false, true, false}, {false, true, false}, {true, true, true}}},
		{{{false, false, true}, {true, true, true}, {false, false, true}}}
	};
    
    public static boolean[][][][] lPentBool = { // L pieces
		{{{true}, {false}}, {{true}, {false}}, {{true}, {false}}, {{true}, {true}}},
		{{{false}, {true}}, {{false}, {true}}, {{false}, {true}}, {{true}, {true}}},
		{{{true}, {true}}, {{true}, {false}}, {{true}, {false}}, {{true}, {false}}},
		{{{true}, {true}}, {{false}, {true}}, {{false}, {true}}, {{false}, {true}}},
		{{{true}, {true}, {true}, {true}}, {{true}, {false}, {false}, {false}}},
		{{{true}, {true}, {true}, {true}}, {{false}, {false}, {false}, {true}}},
		{{{true}, {false}, {false}, {false}}, {{true}, {true}, {true}, {true}}},
		{{{false}, {false}, {false}, {true}}, {{true}, {true}, {true}, {true}}},
		{{{true, false}}, {{true, false}}, {{true, false}}, {{true, true}}},
		{{{false, true}}, {{false, true}}, {{false, true}}, {{true, true}}},
		{{{true, true}}, {{true, false}}, {{true, false}}, {{true, false}}},
		{{{true, true}}, {{false, true}}, {{false, true}}, {{false, true}}},
		{{{true, true}, {true, false}, {true, false}, {true, false}}},
		{{{false, true}, {false, true}, {false, true}, {true, true}}},
		{{{true, true}, {false, true}, {false, true}, {false, true}}},
		{{{true, false}, {true, false}, {true, false}, {true, true}}},
		{{{true, true, true, true}}, {{true, false, false, false}}},
		{{{true, true, true, true}}, {{false, false, false, true}}},
		{{{true, false, false, false}}, {{true, true, true, true}}},
		{{{false, false, false, true}}, {{true, true, true, true}}},
		{{{true, false, false, false}, {true, true, true, true}}},
		{{{true, true, true, true}, {false, false, false, true}}},
		{{{true, true, true, true}, {true, false, false, false}}},
		{{{false, false, false, true}, {true, true, true, true}}}
	};
    
    public static boolean[][][][] pPentBool = { // P pieces
		{{{true}, {false}}, {{true}, {true}}, {{true}, {true}}},
		{{{true}, {true}}, {{true}, {true}}, {{false}, {true}}},
		{{{true}, {true}}, {{true}, {true}}, {{true}, {false}}},		
		{{{false}, {true}}, {{true}, {true}}, {{true}, {true}}},
		{{{true}, {true}, {true}}, {{true}, {true}, {false}}},
		{{{true}, {true}, {true}}, {{false}, {true}, {true}}},
		{{{false}, {true}, {true}}, {{true}, {true}, {true}}},
		{{{true}, {true}, {false}}, {{true}, {true}, {true}}},
		{{{true, false}}, {{true, true}}, {{true, true}}},
		{{{false, true}}, {{true, true}}, {{true, true}}},
		{{{true, true}}, {{true, true}}, {{false, true}}},
		{{{true, true}}, {{true, true}}, {{true, false}}},
		{{{true, true}, {true, true}, {true, false}}},
		{{{false, true}, {true, true}, {true, true}}},
		{{{true, true}, {true, true}, {false, true}}},
		{{{true, false}, {true, true}, {true, true}}},
		{{{true, true, false}}, {{true, true, true}}},
		{{{true, true, true}}, {{true, true, false}}},
		{{{true, true, true}}, {{false, true, true}}},
		{{{false, true, true}}, {{true, true, true}}},
		{{{true, true, false}, {true, true, true}}},
		{{{true, true, true}, {false, true, true}}},
		{{{true, true, true}, {true, true, false}}},
		{{{false, true, true}, {true, true, true}}}
	};

	/**
    * get pentominoes database
    * @return database of pentominoes
    */

	public static boolean[][][][][] getDatabase() {
		boolean[][][][][] pentominoes = {lPentBool, pPentBool, tPentBool};
        return pentominoes;
    }
}
