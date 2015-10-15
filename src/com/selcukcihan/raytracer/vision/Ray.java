/**
 * @date	: 28.Mar.2009 
 * @author	: selcuk
 * @project	: RayTracer
 * @package	: com.selcukcihan.raytracer.vision
 */
package com.selcukcihan.raytracer.vision;

import com.selcukcihan.raytracer.math.Point3;
import com.selcukcihan.raytracer.math.Vector3;

/**
 * @author selcuk
 *
 */
public class Ray
{
	public static final double EPSILON = 0.00001;
	
	public enum RayType
	{
		EYE, /* ray stems from eye point */
		REFLECTED, /* ray is a reflected ray */
		REFRACTED, /* ray is a refracted ray */
		SHADOW /* shadow ray */
	};
	
	public final RayType Type;
	private Point3 m_p = null;
	private Vector3 m_u = null; /* unit direction vector */
	
	public Ray(Point3 p_from, Point3 p_to, RayType p_type) /* ray starts from p_from and extends to p_to and further away*/
	{
		m_u = new Vector3(p_to.sub(p_from)).getNormalized();
		m_p = new Point3(p_from);
		Type = p_type;
	}
	
	public Ray(Point3 p_from, Vector3 p_to, RayType p_type)
	{
		/* her rayin başında bir obje varmış gibi düşünüyoruz
		 * sadece kameradan gelen primary rayin başında bir obje yok
		 * 
		 * p_isect.point'ten baslayip p_to dogrultusunda giden rayi construct ediyoruz
		 */
		m_u = p_to.getNormalized();
		m_p = new Point3(p_from);
		Type = p_type;
	}
	
	public Point3 point() { return new Point3(m_p); } /* rayin baslangic noktasi */
	public Vector3 direction() { return new Vector3(m_u); } /* rayin directionu, normalized */

	public Intersection closerIntersection(Intersection p_a,
			Intersection p_b)
	{
		/*
		 * returns either p_a or p_b by selecting the one which is closer
		 * to this ray's originating point.
		 */
				
		if(m_p.distanceSquared(p_a.point()) > m_p.distanceSquared(p_b.point()))
			return p_b;
		else
			return p_a;
	}
}
