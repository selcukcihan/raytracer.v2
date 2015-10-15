package com.selcukcihan.raytracer.elements;

import java.util.LinkedList;
import java.util.Vector;

import javax.media.j3d.Transform3D;

import com.selcukcihan.raytracer.math.Point3;
import com.selcukcihan.raytracer.math.Vector3;
import com.selcukcihan.raytracer.vision.Intersection;
import com.selcukcihan.raytracer.vision.Ray;
import com.selcukcihan.raytracer.vision.Ray.RayType;

public class TriangulatedMesh implements IGeometry
{
	private LinkedList<int []> m_faceIndexSet = null;
	private Vector<Point3> m_vertices = null;

	public TriangulatedMesh()
	{
		m_faceIndexSet = new LinkedList<int[]>();
		m_vertices = new Vector<Point3>();
	}
	
	public void addVertex(Point3 p_vertex) { m_vertices.add(p_vertex); }
	public void addFace(int p_index0, int p_index1, int p_index2)
	{
		m_faceIndexSet.push(new int[]{p_index0, p_index1, p_index2});
	}
	
	private Intersection intersectTriangle(int [] p_triangle,
			Ray p_ray, Transform3D p_xform, Drawable p_d)
	{
		/*
		 * http://www.lighthouse3d.com/opengl/maths/index.php?raytriint
		 */
//		if(p_ray.object() == p_d)
//		{
//			if(p_ray.Type == RayType.SHADOW)
//			{
//				if()
//			}
//		}
//		Intersection retVal = null;
//		Point3 isectPoint = null;
//		Vector3 isectPointNormal = null;
//		
//		Point3 p0 = new Point3(m_vertices.get(p_triangle[0]));
//		Point3 p1 = new Point3(m_vertices.get(p_triangle[1]));
//		Point3 p2 = new Point3(m_vertices.get(p_triangle[2]));
//		
//		Vector3 normal = (new Vector3(p1.sub(p0))).cross(new Vector3(p2.sub(p1))).getNormalized(); /* surface normal */
//		
//		p_xform.transform(p0);
//		p_xform.transform(p1);
//		p_xform.transform(p2);
//		
//		Vector3 e1 = new Vector3(p1.sub(p0));
//		Vector3 e2 = new Vector3(p2.sub(p0));
//		
//		Vector3 h = p_ray.direction().cross(e2);
//
//		double a = e1.dot(h);
//		
//		if (a > -0.00001 && a < 0.00001)
//			return null;
//		
//		double f = 1 / a;
//		Vector3 s = new Vector3(p_ray.point().sub(p0));
//
//		double u = f * (s.dot(h));
//		
//		if (u < 0.0 || u > 1.0)
//			return null;
//		
//		Vector3 q = s.cross(e1);
//
//		double v = f * (p_ray.direction().dot(q));
//		if (v < 0.0 || u + v > 1.0)
//			return null;
//		// at this stage we can compute t to find out where 
//		// the intersection point is on the line
//		double t = f * (e2.dot(q));
//		if (t >= 0) // ray intersection
//		{
//			isectPoint = new Point3(p_ray.direction());
//			isectPoint.scaleAdd(t, p_ray.point()); /* candidate intersection point */
//			
//			if(p_ray.Type == RayType.EYE)
//			{
//				isectPointNormal = new Vector3(normal);
//				retVal = new Intersection(isectPoint, isectPointNormal, p_d, p_ray);
//				return retVal;
//			}
//			else if(p_ray.Type == RayType.SHADOW)
//			{
//				
//			}
//			else /* if p_ray.Type == RayType.REFLECTED_OR_REFRACTED */ 
//			{
//				
//			}
//			//if(p_ray.isShadow())
//			if(true)
//			{
//				if((p_ray.object() == p_d) && (isectPoint.epsilonEquals(p_ray.point(), 0.00001)))
//				{
//					/* kesilen obje rayin originate ettigi obje ve kestigi nokta rayin originate ettigi */
//				}
//			}
//			else
//			{
//				
//			}
//		}
//		else // this means that there is a line intersection  
//			 // but not a ray intersection
//			 return null;

		return null;
	}
	
	public Intersection intersect(Ray p_ray, Transform3D p_xform, Drawable p_d)
	{
		for(int [] face : m_faceIndexSet)
		{
			Intersection isect = intersectTriangle(face, p_ray, p_xform, p_d);
			if(isect != null)
				return isect;
		}
		return null;
	}

	/* (non-Javadoc)
	 * @see com.selcukcihan.raytracer.elements.IGeometry#inside(com.selcukcihan.raytracer.math.Point3, javax.media.j3d.Transform3D)
	 */
	@Override
	public boolean inside(Point3 p_point, Transform3D p_xform)
	{
		// TODO Auto-generated method stub
		return false;
	}

}
