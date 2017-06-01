package com.spaylia.delay;

import android.view.View;

public class Player {

	private long tStart = 0;
	private long tDelta = 0;

	private boolean firstTime = true;
	private boolean moveable = true;

	private int x, y;

	private classicLevel cl;
	private classicMap cm;

	public Player(int x, int y, classicLevel cl, classicMap cm) {
		this.x = x;
		this.y = y;
		this.cl = cl;
		this.cm = cm;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void move(View v, int mili, int diff) {
		if(firstTime) {
			tStart = System.currentTimeMillis();
			firstTime = false;
		} else {
			tDelta = System.currentTimeMillis() - tStart;
			firstTime = true;
		}

		if(tDelta < mili) {
			if (v.equals(cl.getIbUp())) {
				move(1, 0, 'l', diff);				//1 row less
			} else if (v.equals(cl.getIbDown())) {
				move(1, 0, 'm', diff);				//1 row more
			} else if (v.equals(cl.getIbLeft())) {
				move(0, 1, 'l', diff);				//1 column less
			} else if (v.equals(cl.getIbRight())) {
				move(0, 1, 'm', diff);				//1 column more
			}
		} else
			cl.loose();
	}

	public void move(int x, int y, char c, int diff) {
		/* 'l' for "less" */
		if(c == 'l') {				//up and left
			if(cm.getMap()[this.x - x][this.y - y] == 1 && moveable && diff > 1)		//equals to a wall if Hard or Insane
				cl.loose();
			if(cm.getMap()[this.x - x][this.y - y] == 0 && moveable) {				//equals to the path
				if(cm.getMap()[this.x][this.y] == 2 && moveable)						//set the color fo the first case
					cl.getIv(this.x, this.y).setImageResource(R.drawable.start);
				else
					cl.getIv(this.x, this.y).setImageResource(R.drawable.path);
				cl.getIv(this.x-x, this.y-y).setImageResource(R.drawable.player);
				this.x -= x;
				this.y -= y;
			} else if(cm.getMap()[this.x - x][this.y - y] == 3 && moveable) {		//equals to the final case
				cl.getIv(this.x, this.y).setImageResource(R.drawable.path);
				cl.getIv(this.x-x, this.y-y).setImageResource(R.drawable.player);
				this.x -= x;
				this.y -= y;
				cl.win();
			} else if(cm.getMap()[this.x - x][this.y - y] == 2 && moveable) {		//equals to the first case
				cl.getIv(this.x, this.y).setImageResource(R.drawable.path);
				cl.getIv(this.x-x, this.y-y).setImageResource(R.drawable.player);
				this.x -= x;
				this.y -= y;
			}
		}
		/* 'm' for "more" */
		else if(c == 'm') {			//down and right
			if(cm.getMap()[this.x + x][this.y + y] == 1 && moveable && diff > 1)
				cl.loose();
			if(cm.getMap()[this.x + x][this.y + y] == 0 && moveable) {
				if(cm.getMap()[this.x][this.y] == 2 && moveable)
					cl.getIv(this.x, this.y).setImageResource(R.drawable.start);
				else
					cl.getIv(this.x, this.y).setImageResource(R.drawable.path);
				cl.getIv(this.x+x, this.y+y).setImageResource(R.drawable.player);
				this.x += x;
				this.y += y;
			} else if(cm.getMap()[this.x + x][this.y + y] == 3 && moveable) {
				cl.getIv(this.x, this.y).setImageResource(R.drawable.path);
				cl.getIv(this.x+x, this.y+y).setImageResource(R.drawable.player);
				this.x += x;
				this.y += y;
				cl.win();
			} else if(cm.getMap()[this.x + x][this.y + y] == 2 && moveable) {
				cl.getIv(this.x, this.y).setImageResource(R.drawable.path);
				cl.getIv(this.x+x, this.y+y).setImageResource(R.drawable.player);
				this.x += x;
				this.y += y;
			}
		}
	}

	public void setFirstTime(boolean b) {
		firstTime = b;
	}
	public void setMoveable(boolean b) {
		moveable = b;
	}
	public void setTDelta(int i) {
		tDelta = i;
	}
}