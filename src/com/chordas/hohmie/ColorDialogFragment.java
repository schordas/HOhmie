package com.chordas.hohmie;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;

public class ColorDialogFragment extends DialogFragment{
	
	private int mColorArrayIndex;
	
	public ColorDialogFragment(int mColorArrayIndex){
		this.mColorArrayIndex = mColorArrayIndex;
	}
	
	private String[] mColorArray;
	public interface NoticeDialogListener{
		public void onDialogPositiveClick(DialogFragment dialog, String color, int colorIndex);
		public void onDialogNegativeClick(DialogFragment dialog);
	}
	
	NoticeDialogListener mListener;
	
	@Override
	public void onAttach(Activity activity){
		super.onAttach(activity);
		
		try{
			mListener = (NoticeDialogListener) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(activity.toString() 
					+ " must implement NoticeDialogListener");
		}
	}
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState){
		AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
		builder.setTitle(R.string.band_color_prompt);
		mColorArray = getResources().getStringArray(R.array.color_array);
		builder.setSingleChoiceItems(mColorArray, mColorArrayIndex, null);
		builder.setPositiveButton(R.string.ok, new Dialog.OnClickListener() {
			public void onClick(DialogInterface dialog, int whichButton) {
				dialog.dismiss();
				int selectedPosition = ((AlertDialog)dialog).getListView().getCheckedItemPosition();
				String color = mColorArray[selectedPosition];
				mListener.onDialogPositiveClick(ColorDialogFragment.this, color, selectedPosition);
			}
		});
		builder.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener(){
			public void onClick(DialogInterface dialog, int id){
				dialog.dismiss();
			}
		});
		return builder.create();
	}
}
