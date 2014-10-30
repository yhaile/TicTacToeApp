package com.yonashaile.tictactoeapp;


import java.util.ArrayList;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Point;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.Toast;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuInflater;
import com.actionbarsherlock.view.MenuItem;
import com.yonashaile.tictactoeapp.R;

/**
 * @author yhaile
 * extends Activity
 */
public class MainActivity extends SherlockActivity {
	
	// Declared variables
	Paint paint3 = new Paint();
	Paint paint2 = new Paint();
	Paint paint = new Paint();
	Point pt = new Point();
	String cell = "";
	boolean xbool = true;
	int xWins = 0;
	int oWins = 0;
	int tieGames = 0;
	Canvas publicCanvas;
	Context context;
	DemoView demoview;
	
	// Saved Instance State KEYS
	private static final String KEY_XWINS = "xScore";
	private static final String KEY_OWINS = "yScore";
	private static final String KEY_TIES = "totalTies";
	private static final String KEY_AVAIL = "availibleCells";
	private static final String KEY_PLAYED = "playedCells";
	private static final String KEY_XCELLS = "xCells";
	private static final String KEY_OCELLS = "oCells";
	private static final String KEY_XPOINTS = "xCoordinates";
	private static final String KEY_OPOINTS = "oCoordinates";
		
	// Create the cell that make up the game board with Rectangles
	Rect cell1 = new Rect(40, 70, 177, 207);
	Rect cell2 = new Rect(180, 70, 320, 207);
	Rect cell3 = new Rect(323, 70, 460, 207);
	Rect cell4 = new Rect(40, 210, 177, 350);
	Rect cell5 = new Rect(180, 210, 320, 350);
	Rect cell6 = new Rect(323, 210, 460, 350);
	Rect cell7 = new Rect(40, 353, 177, 490);
	Rect cell8 = new Rect(180, 353, 320, 490);
	Rect cell9 = new Rect(323, 353, 460, 490);
	
	// Declared ArrayLists and class instances
	ArrayList<XPointClass> xPointsArray = new ArrayList<XPointClass>();
	ArrayList<OPointClass> oPointsArray = new ArrayList<OPointClass>();
	ArrayList<String> availibleCells = new ArrayList<String>();
	ArrayList<String> playedCells = new ArrayList<String>();
	ArrayList<String> xCells = new ArrayList<String>();
	ArrayList<String> oCells = new ArrayList<String>();
	
	// Declared class instances
	XPointClass xPointObject;
	OPointClass oPointObject;
	
	/** Called when the activity is first created. */
	/* @param savedInstanceState
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		demoview = new DemoView(this);
		setContentView(demoview);
		
		// getting values passed through savedInstanceState
		 if (savedInstanceState != null) {
			 xWins = savedInstanceState.getInt(KEY_XWINS);
			 oWins = savedInstanceState.getInt(KEY_OWINS);
			 tieGames = savedInstanceState.getInt(KEY_TIES);
			 availibleCells = savedInstanceState.getStringArrayList(KEY_AVAIL);
			 playedCells = savedInstanceState.getStringArrayList(KEY_PLAYED);
			 xCells = savedInstanceState.getStringArrayList(KEY_XCELLS);
			 oCells = savedInstanceState.getStringArrayList(KEY_OCELLS);
			 xPointsArray = savedInstanceState.getParcelableArrayList(KEY_XPOINTS);
			 oPointsArray = savedInstanceState.getParcelableArrayList(KEY_OPOINTS);
			 
			 // This snippet check for a win or a tie when orientation changes and savedInstance state
			 // is passed and disables the board.
			 if((xCells.contains("1") && xCells.contains("2") && xCells.contains("3")) ||
						(xCells.contains("4") && xCells.contains("5") && xCells.contains("6")) ||
						(xCells.contains("7") && xCells.contains("8") && xCells.contains("9")) || 
						(xCells.contains("1") && xCells.contains("4") && xCells.contains("7")) || 
						(xCells.contains("2") && xCells.contains("5") && xCells.contains("8")) ||
						(xCells.contains("3") && xCells.contains("6") && xCells.contains("9")) ||
						(xCells.contains("1") && xCells.contains("5") && xCells.contains("9")) ||
						(xCells.contains("3") && xCells.contains("5") && xCells.contains("7")) ||
						(oCells.contains("1") && oCells.contains("2") && oCells.contains("3")) ||
						(oCells.contains("4") && oCells.contains("5") && oCells.contains("6")) ||
						(oCells.contains("7") && oCells.contains("8") && oCells.contains("9")) || 
						(oCells.contains("1") && oCells.contains("4") && oCells.contains("7")) || 
						(oCells.contains("2") && oCells.contains("5") && oCells.contains("8")) ||
						(oCells.contains("3") && oCells.contains("6") && oCells.contains("9")) ||
						(oCells.contains("1") && oCells.contains("5") && oCells.contains("9")) ||
						(oCells.contains("3") && oCells.contains("5") && oCells.contains("7")) ||  
						(availibleCells.contains("1") == false && availibleCells.contains("2") == false &&
						availibleCells.contains("3") == false && availibleCells.contains("4") == false && 
						availibleCells.contains("5") == false && availibleCells.contains("6") == false && 
						availibleCells.contains("7") == false && availibleCells.contains("8") == false && 
						availibleCells.contains("9") == false )){
				 
				 demoview.setOnTouchListener(null);
			 }
	     } else {	
		 
		// Load values into availibleCells ArrayList
		availibleCells.add("5");
		availibleCells.add("1");
		availibleCells.add("3");
		availibleCells.add("7");
		availibleCells.add("9");
		availibleCells.add("2");
		availibleCells.add("4");
		availibleCells.add("6");
		availibleCells.add("8");
		
	     }
	}

	/**
	 * @author yhaile
	 * @extends View
	 * @implements OnTouchListener
	 */
	private class DemoView extends View implements OnTouchListener{
		/**
		 * @param context
		 */
		public DemoView(Context context){
			super(context);
			this.setOnTouchListener(this);
		}

		/* 
		 * @see android.view.View#onDraw(android.graphics.Canvas)
		 * @param canvas
		 */
		@SuppressLint("DrawAllocation")
		@Override protected void onDraw(Canvas canvas) {
			super.onDraw(canvas);
			
			xPointObject = new XPointClass();
			oPointObject = new OPointClass();
			
			// Draw canvas itself
			paint.setColor(Color.YELLOW);
			canvas.drawPaint(paint);
			
			// Draw game board as series of rectangles
			paint2.setStyle(Style.FILL);
			paint2.setColor(Color.BLACK);
			canvas.drawRect(cell1, paint2);
			canvas.drawRect(cell2, paint2);
			canvas.drawRect(cell3, paint2);
			canvas.drawRect(cell4, paint2);
			canvas.drawRect(cell5, paint2);
			canvas.drawRect(cell6, paint2);
			canvas.drawRect(cell7, paint2);
			canvas.drawRect(cell8, paint2);
			canvas.drawRect(cell9, paint2);
			 
			BlurMaskFilter blurMaskFilter = new BlurMaskFilter(5, BlurMaskFilter.Blur.OUTER);
			
			/*
			 * Output X's and O's by looping through ArrayList of x and y coordinates stored 
			 * from button clicks
			 */
			for (XPointClass object : xPointsArray) {	
				 paint3.setMaskFilter(blurMaskFilter);
				 paint3.setColor(Color.WHITE);
				 paint3.setTextSize(70);
				 canvas.drawText("X", object.getObjectX(), object.getObjectY(), paint3);	
					
			 }
			
			 for (OPointClass object : oPointsArray) {	
				blurMaskFilter = new BlurMaskFilter(5, BlurMaskFilter.Blur.NORMAL);
				paint3.setMaskFilter(blurMaskFilter);
				paint3.setColor(Color.RED);
				paint3.setTextSize(70);
				canvas.drawText("O", object.getObjectX(), object.getObjectY(), paint3);	
					
			}
			
			// Variable to hold this instance of canvas 
			publicCanvas = canvas;
		}

		/* @param arg0
		 * @param arg1
		 * @see android.view.View.OnTouchListener#onTouch(android.view.View, android.view.MotionEvent)
		 * return
		 */
		@Override
		public boolean onTouch(View arg0, MotionEvent arg1) {
			// TODO Auto-generated method stub
			if (arg1.getAction() == MotionEvent.ACTION_DOWN) { 
				pt.x =(int) arg1.getX();
			    pt.y =(int) arg1.getY();
			    
			    // Check if X move was within a game board cell and assign said cell
			    if (cell1.contains((int)arg1.getX(), (int)arg1.getY())) {
			    	cell = "1";
			    } else if (cell2.contains((int)arg1.getX(), (int)arg1.getY())) {
			    	cell = "2";	
			    } else if (cell3.contains((int)arg1.getX(), (int)arg1.getY())) {
				    cell = "3";	
				} else if (cell4.contains((int)arg1.getX(), (int)arg1.getY())) {
				    cell = "4";	
				} else if (cell5.contains((int)arg1.getX(), (int)arg1.getY())) {
				    cell = "5";	
				} else if (cell6.contains((int)arg1.getX(), (int)arg1.getY())) {
				    cell = "6";	
				} else if (cell7.contains((int)arg1.getX(), (int)arg1.getY())) {
				    cell = "7";	
				} else if (cell8.contains((int)arg1.getX(), (int)arg1.getY())) {
				    cell = "8";	
				} else if (cell9.contains((int)arg1.getX(), (int)arg1.getY())) {
				    cell = "9";
				} else {
					xbool = false;
					Toast.makeText(getApplicationContext(), "Must click within the squares", Toast.LENGTH_SHORT).show();
				}
					 if (playedCells.contains(cell) == true){
						Toast.makeText(getApplicationContext(), "Either this square is already picked or the game is over", Toast.LENGTH_SHORT).show();
						xbool = false;
						
					 } else if(availibleCells.contains(cell) == true){
						 xbool = true;
					
				// Set coordinates of where X plays based of cell if it is in availibleCells ArrayList
				if(cell == "1" && availibleCells.contains("1")){
					xPointObject.setObjectX(90);
					xPointObject.setObjectY(160);
			
				} else if(cell == "2" && availibleCells.contains("2")){
					xPointObject.setObjectX(230);
					xPointObject.setObjectY(160);	
			
				} else if(cell == "3" && availibleCells.contains("3")){
					xPointObject.setObjectX(370);
					xPointObject.setObjectY(160);
							
				} else if(cell == "4" && availibleCells.contains("4")){
					xPointObject.setObjectX(90);
					xPointObject.setObjectY(300);
							
				}else if(cell == "5" && availibleCells.contains("5")){
					xPointObject.setObjectX(230);
					xPointObject.setObjectY(300);
							
				}else if(cell == "6" && availibleCells.contains("6")){
					xPointObject.setObjectX(370);
					xPointObject.setObjectY(300);
							
				}else if(cell == "7" && availibleCells.contains("7")){
					xPointObject.setObjectX(90);
					xPointObject.setObjectY(440);
							
				} else if(cell == "8" && availibleCells.contains("8")){
					xPointObject.setObjectX(230);
					xPointObject.setObjectY(440);

				}else if(cell == "9" && availibleCells.contains("9")){
					xPointObject.setObjectX(370);
					xPointObject.setObjectY(440);
							
				}
				
				// Adjust ArrayList items of just played cell 
				availibleCells.remove(cell);
				playedCells.add(cell);
				xCells.add(cell);
					 
					 }
					 
					 // Check for X wins
					 if(((xCells.contains("1") && xCells.contains("2") && xCells.contains("3")) && availibleCells.contains("x") == false)||
						((xCells.contains("4") && xCells.contains("5") && xCells.contains("6")) && availibleCells.contains("x") == false)||
						((xCells.contains("7") && xCells.contains("8") && xCells.contains("9")) && availibleCells.contains("x") == false)|| 
						((xCells.contains("1") && xCells.contains("4") && xCells.contains("7")) && availibleCells.contains("x") == false)|| 
						((xCells.contains("2") && xCells.contains("5") && xCells.contains("8")) && availibleCells.contains("x") == false)||
						((xCells.contains("3") && xCells.contains("6") && xCells.contains("9")) && availibleCells.contains("x") == false)||
						((xCells.contains("1") && xCells.contains("5") && xCells.contains("9")) && availibleCells.contains("x") == false)|| 
						((xCells.contains("3") && xCells.contains("5") && xCells.contains("7")) && availibleCells.contains("x") == false)){
						 
						 	Toast.makeText(getApplicationContext(), "X WINS!!!", Toast.LENGTH_LONG).show();
						 	String list2[] = new String[availibleCells.size()];
						    list2 = availibleCells.toArray(list2);
						    
						    for (String number : list2) {
						    	playedCells.add(number);
						    	
						    }
						    
						 availibleCells.clear();
						 availibleCells.add("x");
						 xWins++;
						 demoview.setOnTouchListener(null);


					 }
				
					 if(availibleCells.size() == 0){
						 availibleCells.add("x");
						 postInvalidate();
					 }
			 
					 // This snippet of code is the O block logic starting here
					 if (((xCells.contains("1") && xCells.contains("3")) || (xCells.contains("5") && xCells.contains("8"))) && xbool == true && availibleCells.contains("2")){
							oPointObject.setObjectX(230);
							oPointObject.setObjectY(160);	
							String played = "2";
							availibleCells.remove("2");
							 playedCells.add(played);
							 oCells.add(played);
							 xbool = true;
							 xPointsArray.add(xPointObject);
							 oPointsArray.add(oPointObject);
						
						} else if (((xCells.contains("1") && xCells.contains("2")) || (xCells.contains("6") && xCells.contains("9"))) && xbool == true && availibleCells.contains("3")){
							oPointObject.setObjectX(370);
							oPointObject.setObjectY(160);	
							String played = "3";
							availibleCells.remove("3");
							playedCells.add(played);
							oCells.add(played);
							xbool = true;
							xPointsArray.add(xPointObject);
							oPointsArray.add(oPointObject);
			
						} else if (((xCells.contains("2") && xCells.contains("3")) || (xCells.contains("4") && xCells.contains("7"))) && xbool == true && availibleCells.contains("1")){
							oPointObject.setObjectX(90);
							oPointObject.setObjectY(160);	
							String played = "1";
							availibleCells.remove("1");
							playedCells.add(played);
							oCells.add(played);
							xbool = true;
							xPointsArray.add(xPointObject);
							oPointsArray.add(oPointObject);
			
						} else if (((xCells.contains("5") && xCells.contains("6")) || (xCells.contains("1") && xCells.contains("7"))) && xbool == true && availibleCells.contains("4")){
							oPointObject.setObjectX(90);
							oPointObject.setObjectY(300);	
							String played = "4";
							availibleCells.remove("4");
							playedCells.add(played);
							oCells.add(played);
							xbool = true;
							xPointsArray.add(xPointObject);
							oPointsArray.add(oPointObject);
			
						} else if (((xCells.contains("4") && xCells.contains("6")) || (xCells.contains("2") && xCells.contains("8"))) && xbool == true && availibleCells.contains("5")){
							oPointObject.setObjectX(230);
							oPointObject.setObjectY(300);	
							String played = "5";
							availibleCells.remove("5");
							playedCells.add(played);
							oCells.add(played);
							xbool = true;
							xPointsArray.add(xPointObject);
							oPointsArray.add(oPointObject);
			
						} else if (((xCells.contains("4") && xCells.contains("5")) || (xCells.contains("3") && xCells.contains("9"))) && xbool == true && availibleCells.contains("6")){
							oPointObject.setObjectX(370);
							oPointObject.setObjectY(300);	
							String played = "6";
							availibleCells.remove("6");
							playedCells.add(played);
							oCells.add(played);
							xbool = true;
							xPointsArray.add(xPointObject);
							oPointsArray.add(oPointObject);
			
						}  else if (((xCells.contains("8") && xCells.contains("9")) || (xCells.contains("1") && xCells.contains("4"))) && xbool == true && availibleCells.contains("7")){
							oPointObject.setObjectX(90);
							oPointObject.setObjectY(440);	
							String played = "7";
							availibleCells.remove("7");
							playedCells.add(played);
							oCells.add(played);
							xbool = true;
							xPointsArray.add(xPointObject);
							oPointsArray.add(oPointObject);
			
						} else if (((xCells.contains("7") && xCells.contains("9")) || (xCells.contains("2") && xCells.contains("5"))) && xbool == true && availibleCells.contains("8")){
							oPointObject.setObjectX(230);
							oPointObject.setObjectY(440);	
							String played = "8";
							availibleCells.remove("8");
							playedCells.add(played);
							oCells.add(played);
							xbool = true;
							xPointsArray.add(xPointObject);
							oPointsArray.add(oPointObject);
			
						}   else if (((xCells.contains("7") && xCells.contains("8")) || (xCells.contains("3") && xCells.contains("6"))) && xbool == true && availibleCells.contains("9")){
							oPointObject.setObjectX(370);
							oPointObject.setObjectY(440);	
							String played = "9";
							availibleCells.remove("9");
							playedCells.add(played);
							oCells.add(played);
							xbool = true;
							xPointsArray.add(xPointObject);
							oPointsArray.add(oPointObject);
			
						} else if(availibleCells.get(0) == "1" && xbool == true && availibleCells.size() > 0){
				
							// Set coordinates of where X plays based of cell if it is in availibleCells ArrayList
							oPointObject.setObjectX(90);
							oPointObject.setObjectY(160);
							oMoveLogicSeries();
	 
						}else if(availibleCells.get(0) == "2" && xbool == true && availibleCells.size() > 0){
							oPointObject.setObjectX(230);
							oPointObject.setObjectY(160);	
							oMoveLogicSeries();

						}else if(availibleCells.get(0) == "3" && xbool == true && availibleCells.size() > 0){
							oPointObject.setObjectX(370);
							oPointObject.setObjectY(160);
							oMoveLogicSeries();

						} else if(availibleCells.get(0) == "4" && xbool == true && availibleCells.size() > 0){
							oPointObject.setObjectX	(90);
							oPointObject.setObjectY(300);
							oMoveLogicSeries();

						}else if(availibleCells.get(0) == "5" && xbool == true && availibleCells.size() > 0){
							oPointObject.setObjectX(230);
							oPointObject.setObjectY(300);
							oMoveLogicSeries();
				
						}else if(availibleCells.get(0) == "6" && xbool == true && availibleCells.size() > 0){
							oPointObject.setObjectX(370);
							oPointObject.setObjectY(300);
							oMoveLogicSeries();
				
						}else if(availibleCells.get(0) == "7" && xbool == true && availibleCells.size() > 0){
							oPointObject.setObjectX(90);
							oPointObject.setObjectY(440);
							oMoveLogicSeries();
				
						} else if(availibleCells.get(0) == "8" && xbool == true && availibleCells.size() > 0){
							oPointObject.setObjectX(230);
							oPointObject.setObjectY(440);
							oMoveLogicSeries();
								
						}else if(availibleCells.get(0) == "9" && xbool == true && availibleCells.size() > 0){
							oPointObject.setObjectX(370);
							oPointObject.setObjectY(440);
							oMoveLogicSeries();

						} else if (availibleCells.get(0) == "x"){
							xPointsArray.add(xPointObject);
							Toast.makeText(getApplicationContext(), "Game Over!!!", Toast.LENGTH_LONG).show();
								
							if((xCells.contains("1") && xCells.contains("2") && xCells.contains("3")) ||
								(xCells.contains("4") && xCells.contains("5") && xCells.contains("6")) ||
								(xCells.contains("7") && xCells.contains("8") && xCells.contains("9")) || 
								(xCells.contains("1") && xCells.contains("4") && xCells.contains("7")) || 
								(xCells.contains("2") && xCells.contains("5") && xCells.contains("8")) ||
								(xCells.contains("3") && xCells.contains("6") && xCells.contains("9")) ||
								(xCells.contains("1") && xCells.contains("5") && xCells.contains("9")) ||
								(xCells.contains("3") && xCells.contains("5") && xCells.contains("7"))){
							} else {
									 
								tieGames++;
								demoview.setOnTouchListener(null);
								Toast.makeText(getApplicationContext(), "TIE", Toast.LENGTH_SHORT).show();
							}
						}
			 
			 if((oCells.contains("1") && oCells.contains("2") && oCells.contains("3")) ||
				(oCells.contains("4") && oCells.contains("5") && oCells.contains("6")) ||
				(oCells.contains("7") && oCells.contains("8") && oCells.contains("9")) || 
				(oCells.contains("1") && oCells.contains("4") && oCells.contains("7")) || 
				(oCells.contains("2") && oCells.contains("5") && oCells.contains("8")) ||
				(oCells.contains("3") && oCells.contains("6") && oCells.contains("9")) ||
				(oCells.contains("1") && oCells.contains("5") && oCells.contains("9")) ||
				(oCells.contains("3") && oCells.contains("5") && oCells.contains("7"))){
				 	Toast.makeText(getApplicationContext(), "O WINS!!!", Toast.LENGTH_LONG).show();
				 	String list2[] = new String[availibleCells.size()];
				 	list2 = availibleCells.toArray(list2);
				 	
				 	for (String number : list2) {
				 		playedCells.add(number);
				 	}
					
				 	availibleCells.clear();
				 	availibleCells.add("x");
				 	oWins++;
				 	demoview.setOnTouchListener(null);	 
			 }
			
			 invalidate();
		}
		
			return true;
			
	}
		
		 public void clearIt(){
		    	publicCanvas.drawColor(Color.YELLOW);
		        invalidate();
		 }
		 
		 public void oMoveLogicSeries(){
			 String played = availibleCells.get(0);
			 availibleCells.remove(0);
			 playedCells.add(played);
			 oCells.add(played);
			 xbool = true;
			 xPointsArray.add(xPointObject);
			 oPointsArray.add(oPointObject);
		 }
	}
	
	 /*
	  *@param menu
	 * @see android.app.Activity#onCreateOptionsMenu(android.view.Menu)
	 */
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		 MenuInflater inflater = getSupportMenuInflater();
		 	   inflater.inflate(R.menu.main, menu);
		
    	return true;
    }
		 
	/* @param item
	 * @see android.app.Activity#onOptionsItemSelected(android.view.MenuItem)
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {  
	        switch (item.getItemId()) {  
	            case R.id.item1: 
	            	// Clear board and reset ArrayLists and variable values
	            	xPointsArray.clear();
	            	oPointsArray.clear();
	            	xCells.clear();
	            	oCells.clear();
	            	playedCells.clear();
	            	availibleCells.clear();
	            	availibleCells.add("5");
	        		availibleCells.add("1");
	        		availibleCells.add("3");
	        		availibleCells.add("7");
	        		availibleCells.add("9");
	        		availibleCells.add("2");
	        		availibleCells.add("4");
	        		availibleCells.add("6");
	        		availibleCells.add("8");
	        		xbool = true;
	        		cell = "";
	        		demoview.setOnTouchListener(demoview);
	        		(demoview).clearIt();
	        		return true;     
	  
	            case R.id.item2:  
	                Toast.makeText(getApplicationContext(),"X Wins: " + xWins + "\nO Wins: " + oWins + "\nTie Games: " + tieGames,Toast.LENGTH_LONG).show();  
	                return true;     
	             
	            default:  
	                return super.onOptionsItemSelected((android.view.MenuItem) item);  
	        }  
	    }
	
	/* (non-Javadoc)
	 * @see android.app.Activity#onSaveInstanceState(android.os.Bundle)
	 * @param savedInstanceState
	 */
	@Override
	public void onSaveInstanceState(Bundle savedInstanceState) {
		super.onSaveInstanceState(savedInstanceState);
		
		// set bundle key value pairs for savedInstanceState
	    savedInstanceState.putInt(KEY_XWINS, xWins);
	    savedInstanceState.putInt(KEY_OWINS, oWins);
	    savedInstanceState.putInt(KEY_TIES, tieGames);
	    savedInstanceState.putStringArrayList(KEY_AVAIL, availibleCells);
	    savedInstanceState.putStringArrayList(KEY_PLAYED, playedCells);
	    savedInstanceState.putStringArrayList(KEY_XCELLS, xCells);
	    savedInstanceState.putStringArrayList(KEY_OCELLS, oCells);
	    savedInstanceState.putParcelableArrayList(KEY_XPOINTS, xPointsArray);
	    savedInstanceState.putParcelableArrayList(KEY_OPOINTS, oPointsArray);
	    
	}
}