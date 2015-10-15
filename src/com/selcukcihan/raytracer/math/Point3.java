package com.selcukcihan.raytracer.math;

import javax.vecmath.Point3d;
import javax.vecmath.Point3f;
import javax.vecmath.Tuple3d;
import javax.vecmath.Tuple3f;

public class Point3 extends Point3d
{

	/**
	 * 
	 */
	private static final long serialVersionUID = 3246427428073016996L;

	public Point3()
	{
		super();
	}

	public Point3(double[] p_arg0)
	{
		super(p_arg0);

	}

	public Point3(Point3d p_arg0)
	{
		super(p_arg0);

	}

	public Point3(Point3f p_arg0)
	{
		super(p_arg0);

	}

	public Point3(Tuple3f p_arg0)
	{
		super(p_arg0);

	}

	public Point3(Tuple3d p_arg0)
	{
		super(p_arg0);

	}

	public Point3(double p_arg0, double p_arg1, double p_arg2)
	{
		super(p_arg0, p_arg1, p_arg2);

	}

	public Point3 sub(Point3 p_arg0) /* return (this - p_arg0) */
	{
		Point3 retVal = new Point3(this);
		retVal.sub((Tuple3d)p_arg0);
		return retVal;
	}
	
	public Point3 add(Point3 p_arg0) /* return (this + p_arg0) */
	{
		Point3 retVal = new Point3(this);
		retVal.add((Tuple3d)p_arg0);
		return retVal;
	}
	
	public Point3 add(Vector3 p_arg0) /* return (this + p_arg0) */
	{
		return add(new Point3(p_arg0));
	}
	
	public Point3 shift(double p_amount, Vector3 p_direction)
	{
		/*
		 * p_direction is a normalized direction vector
		 * return this + p_amount * p_direction
		 */
		return add(p_direction.mul(p_amount));
	}
	
	public boolean isNaN()
	{
		return (Double.isNaN(x) || Double.isNaN(y) || Double.isNaN(z));
	}
}
