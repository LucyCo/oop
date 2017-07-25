package clids.ex5.crosswords;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * This is a basic implementation of CrosswordShape. 
 * The shape is stored as list of strings.
 * 
 * @author CLIDS
 */
public class MyCrosswordStructure implements CrosswordStructure {
	protected List<String> data = new ArrayList<String>();

	/*
	 * (non-Javadoc)
	 * 
	 * @see CrosswordShape#getHeight()
	 */
	public Integer getHeight() {
		return this.data.size();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see CrosswordShape#getWidth()
	 */
	public Integer getWidth() {
		return (getHeight() != 0) ?  this.data.get(0).length() : 0;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see CrosswordShape#getSlotType(CrosswordPosition)
	 */
	public SlotType getSlotType(CrosswordPosition pos) {
		if (pos.getX() >= getWidth() || pos.getX() < 0 || 
				pos.getY() >= getHeight() || pos.getY() < 0)
			return SlotType.FRAME_SLOT;
		
		switch (this.data.get(pos.getY()).charAt(pos.getX())) {
		case '_':
			return SlotType.UNUSED_SLOT;
		default:
			return SlotType.FRAME_SLOT;
		}
	}
	/*
	 * (non-Javadoc)
	 * @see clids.ex5.crosswords.CrosswordStructure#getSlotTypeByPos(int, int)
	 */
	//we added this method which returns the type of the slot (by using the given getSlotType
	//method according to given coordinates)
	public SlotType getSlotTypeByPos(int x,int y) {
		if (x>=getWidth()||x<0||y>=getHeight()||y<0) {
			return SlotType.FRAME_SLOT;
		}
		char charAt = this.data.get(y).charAt(x);
		if(charAt=='_'){
			return SlotType.UNUSED_SLOT;			
		}
		else {
			return SlotType.FRAME_SLOT;			
		}
	}
	/*
	 * (non-Javadoc)
	 * @see clids.ex5.crosswords.CrosswordStructure#load(java.lang.String)
	 */
	public void load(String textFileName) throws IOException {
		Scanner sc=null;
		try {
			this.data = new ArrayList<String>();
			sc = new Scanner(new FileReader(textFileName));
			while (sc.hasNextLine()) {
				this.data.add(sc.nextLine());
			}
		} finally {
			if (sc!=null) sc.close();
		}
	}
}
