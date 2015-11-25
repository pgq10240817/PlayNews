import com.yhpl.utils.Logger;

import play.Application;
import play.GlobalSettings;

public class MyGlobal extends GlobalSettings {

	@Override
	public void onStart(Application arg0) {
		Logger.debug("MyGlobal", "onStart");
		super.onStart(arg0);
	}

	@Override
	public void onStop(Application arg0) {
		Logger.debug("MyGlobal", "onStop");
		super.onStop(arg0);
	}

}
