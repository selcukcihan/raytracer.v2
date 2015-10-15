/**
 * @date	: 28.Mar.2009 
 * @author	: selcuk
 * @project	: RayTracer
 * @package	: com.selcukcihan.raytracer.vision
 */
package com.selcukcihan.raytracer.vision;

import javax.media.j3d.Transform3D;
import javax.vecmath.Point3d;
import javax.vecmath.Point3f;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector3f;

import com.selcukcihan.raytracer.math.Point3;
import com.selcukcihan.raytracer.math.Vector3;
import com.selcukcihan.raytracer.vision.Ray.RayType;
import com.selcukcihan.raytracer.vision.sampling.Sample;

/**
 * @author selcuk
 *
 */
public class Camera
{
	/*
	 * kamera (0,0,0) da negatif z eksenine bakýyor (opengl defaultu gibi)
	 * ve film'i de çok basit yapalým
	 * ayrýca sample'leri de (x,y) deðeri olarak tutalým
	 * image plane kameradan p_frontPlane kadar uzaklýkta olsun
	 * tam ortasýndan baksýn kamera image planenin
	 */
	private Film m_film = null;
	private Transform3D m_xform = null;
	private int m_frontPlane = 0;
	
	public Camera(int p_frontPlane, int p_width, int p_height)
	{
		m_film = new Film(p_width, p_height);
		m_xform = new Transform3D();
		m_frontPlane = p_frontPlane;
	}

	public void lookAt(Point3d p_eye, Point3d p_center, Vector3d p_up)
	{
		m_xform.lookAt(p_eye, p_center, p_up);
	}
	
	public Film getFilm() { return m_film; }

	/**
	 * @param p_sample
	 * @return
	 */
	public Ray generateRay(Sample p_sample)
	{
		Point3 samplePoint = new Point3(p_sample.x, p_sample.y, -m_frontPlane); /* looking down the -ve z axis by default */
		Point3 cameraPoint = new Point3(0, 0, 0);
		
		Transform3D xform = new Transform3D(m_xform);
		xform.invert(); /* kamera mantigi */
		xform.transform(cameraPoint);
		xform.transform(samplePoint);
		
		Vector3 d = (new Vector3(samplePoint.sub(cameraPoint))).getNormalized();
		
		return (new Ray(cameraPoint, d, RayType.EYE));
	}
}
