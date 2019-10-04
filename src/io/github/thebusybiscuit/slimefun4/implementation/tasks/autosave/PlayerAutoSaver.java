package io.github.thebusybiscuit.slimefun4.implementation.tasks.autosave;

import java.util.Iterator;
import java.util.logging.Level;

import io.github.thebusybiscuit.slimefun4.api.researches.PlayerProfile;
import me.mrCookieSlime.Slimefun.api.Slimefun;

public class PlayerAutoSaver implements Runnable {

	@Override
	public void run() {
		Iterator<PlayerProfile> iterator = PlayerProfile.iterator();
		int players = 0;
		
		while (iterator.hasNext()) {
			PlayerProfile profile = iterator.next();
			
			if (profile.isDirty()) {
				players++;
				profile.save();
			}
			
			if (profile.isMarkedForDeletion()) iterator.remove();
		}
		
		if (players > 0) {
			Slimefun.getLogger().log(Level.INFO, "Auto-Saved Player Data for " + players + " Player(s)!");
		}
	}

}