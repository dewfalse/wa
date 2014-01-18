package wa;

import net.minecraftforge.client.event.sound.SoundLoadEvent;
import net.minecraftforge.event.ForgeSubscribe;

public class SoundLoadEventHandler {

	@ForgeSubscribe
	void onSoundLoadEvent(SoundLoadEvent event) {
		event.manager.soundPoolSounds.addSound("wa:koto.ogg");
		event.manager.soundPoolSounds.addSound("wa:taiko.ogg");
	}
}
