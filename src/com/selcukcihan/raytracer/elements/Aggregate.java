/**
 * @date	: 26.Mar.2009 
 * @author	: selcuk
 * @project	: RayTracer
 * @package	: com.selcukcihan.raytracer.elements
 */
package com.selcukcihan.raytracer.elements;

import java.util.LinkedList;

import com.selcukcihan.raytracer.vision.Intersection;
import com.selcukcihan.raytracer.vision.Ray;

/**
 * @author selcuk
 *
 */
public class Aggregate implements IPrimitive
{
	private BBox m_box = null;
	private LinkedList<IPrimitive> m_objects = null;
	
	public Aggregate()
	{
		m_objects = new LinkedList<IPrimitive>();
	}

	/* (non-Javadoc)
	 * @see com.selcukcihan.raytracer.elements.IPrimitive#intersect(com.selcukcihan.raytracer.vision.Ray)
	 */
	@Override
	public Intersection intersect(Ray p_ray)
	{
		/* unutma ki isectler birden fazla olabilir
		 * yani iki obje arka arkaya olsa bi ray shoot etsen ikisini de kesebilir
		 * bunlardan p_ray'in baþlangýç noktasýna en yakýn olaný döndürmeliyiz
		 */
		
		Intersection retVal = null;
		for(IPrimitive primitive : m_objects)
		{
			Intersection isect = primitive.intersect(p_ray);
			if(retVal == null)
				retVal = isect;
			else if(isect != null) /* retVal is not null and isect is not null */
			{
				retVal = p_ray.closerIntersection(isect, retVal);
			}
		}
		return retVal;
	}



	/**
	 * @param p_d
	 */
	public Drawable add(Drawable p_d)
	{
		m_objects.add(p_d);
		
		return p_d;
	}
	
	

}
