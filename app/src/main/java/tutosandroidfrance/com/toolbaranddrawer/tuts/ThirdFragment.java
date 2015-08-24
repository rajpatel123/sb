package tutosandroidfrance.com.toolbaranddrawer.tuts;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import tutosandroidfrance.com.toolbaranddrawer.R;


public class ThirdFragment extends Fragment {
	// Store instance variables
	private String title;
	private int page;
	Button catchitBtn;

	// newInstance constructor for creating fragment with arguments
	public static ThirdFragment newInstance() {
		ThirdFragment fragmentFirst = new ThirdFragment();
		Bundle args = new Bundle();
		fragmentFirst.setArguments(args);
		return fragmentFirst;
	}

	// Store instance variables based on arguments passed
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		page = getArguments().getInt("someInt", 0);
		title = getArguments().getString("someTitle");


	}

	// Inflate the view for the fragment based on layout XML
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.fragment_third, container, false);
//	//	Button btn = (Button) view.findViewById(R.id.btn_catchit);
//		btn.setOnClickListener(new OnClickListener() {
//
//			@Override
//			public void onClick(View v) {
//				// TODO Auto-generated method stub
//
//				Intent i=new Intent(getActivity(),SignUpActivity.class);
//				startActivity(i);
//				getActivity().finish();
//
//
//			}
//		});
		return view;
	}
}