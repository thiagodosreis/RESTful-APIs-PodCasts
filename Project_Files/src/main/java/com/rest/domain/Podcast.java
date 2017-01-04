package com.rest.domain;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlElement;

public class Podcast {
	// Maps a object property to a XML element derived from property name.
	public int id;
	public String feed;
	public String title;
	public String description;
	public String link;
	
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFeed() {
		return feed;
	}
	public void setFeed(String feed) {
		this.feed = feed;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}

/*
<podcast>
	<feed>http://podcast.apple.com/id/182739</feed>
	<title>Jack Joson</title>
	<description>Music from the surf line in Santa Cruz nono nononon ononon ononon ononono</description>
</podcast>

<podcast>
	<feed>http://podcast.apple.com/id/99999</feed>
	<title>Michel Jacson</title>
	<description>A Music from the surf line in Santa Cruz nono nononon ononon ononon ononono</description>
</podcast>
*/