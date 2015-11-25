package app.yhpl.news.fragment;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

public interface FragmentOnClickListener extends OnClickListener {

	public void onClick(View v, Bundle args);

	public void onLongClick(View view, Bundle bundle);

}
