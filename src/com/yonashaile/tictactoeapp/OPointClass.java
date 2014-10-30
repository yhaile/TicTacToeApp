package com.yonashaile.tictactoeapp;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * @author yhaile
 * Class for O or computer player
 */
public class OPointClass implements Parcelable {
	
		 private int objectX,objectY;
		    
		    /**
		     * @return
		     */
		    public int getObjectX() {
		        return objectX;
		    }

		    /**
		     * @param objectX
		     */
		    public void setObjectX(int objectX) {
		        this.objectX = objectX;
		    }

		    /**
		     * @return
		     */
		    public int getObjectY() {
		        return objectY;
		    }

		    /**
		     * @param objectY
		     */
		    public void setObjectY(int objectY) {
		        this.objectY = objectY;
		    }

			@Override
			public int describeContents() {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public void writeToParcel(Parcel dest, int flags) {
				// TODO Auto-generated method stub
				
			}
		    
	}
