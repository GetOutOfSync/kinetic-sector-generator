package entity;

import java.util.ArrayList;

public abstract class TaggedObject {
	
	protected String type;
	protected ArrayList<String> tags;
	protected long id;
	
	public TaggedObject () {
		tags = new ArrayList<String>();
	}
	
	public void addTag(String tag) {
		this.tags.add(tag);
	}
	
	public ArrayList<String> getTags() {
		return this.tags;
	}
	
	public void removeTag(String tag) {
		for (int i = 0; i < tags.size(); i++) {
			if (tag.equals(tags.get(i))) {
				tags.remove(i);
				return;
			}
		}
	}

	public long getID() {
		return this.id;
	}

	public void setID(long id) {
		this.id = id;
	}
	
	public String getType() {
		return this.type;
	}
}
