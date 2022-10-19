package manager;

import entity.Sector;
import rand.Generator;
import system.CoordPair;

public class SectorManager extends Manager {

	private SolarGen solarManager;
	
	private final int maxX = 8;
	private final int maxY = 10;
	private final int solarRoll = 10;
	private final int solarAdd = 20;
	
	public SectorManager() {
		this(new Generator().getSeed());
	}

	public SectorManager(long seed) {
		//Sets up all of the main managers which will handle generating everything at lower levels.
		super(seed);

		
	}
	
	protected void initManagers() {
		solarManager = new SolarGen(gen.genSeed());
		addChildManager(solarManager);
	}
	
	public Sector newSector() {
		Sector workObject = new Sector();
		
		int numSystems = gen.roll(solarRoll, true) + solarAdd;
		CoordPair[] coordList = new CoordPair[numSystems];
		boolean exists = false;
		
		//Create random coordinates on a table until number of systems found.
		for (int i = 0; i < numSystems; i++) {
			do {
				exists = false;
				CoordPair newCoords = new CoordPair(gen.roll(maxX), gen.roll(maxY));
				
				for (CoordPair testCoords : coordList) {
					if (testCoords.isEqual(newCoords)) {
						exists = true;
						break;
					}
				}
				
				if (!exists) coordList[i] = newCoords;
			} while (exists);
		}
		
		//
		
		return workObject;
	}

}
