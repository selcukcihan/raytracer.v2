/**
 * @date	: 31.Mar.2009 
 * @author	: selcuk
 * @project	: RayTracer
 * @package	: com.selcukcihan.raytracer.elements
 */
package com.selcukcihan.raytracer.elements;

import javax.media.j3d.Transform3D;
import javax.vecmath.AxisAngle4d;
import com.selcukcihan.raytracer.math.Vector3;

/**
 * @author selcuk
 *
 */
public abstract class Element
{
	/* transformable objeler bu classý extend etmeli
	 * misal PointLight ve Drawable
	 * yalnýzca xform ile ilgili þeyler olacak burada
	 * misal move, rotate vs.
	 */
	protected Transform3D m_xform = null;
	
	protected Element()
	{
		m_xform = new Transform3D();
	}
	
	public void translate(Vector3 p_delta)
	{
		/* new position = old position + delta */
		
		Transform3D newXForm = new Transform3D();
		newXForm.setTranslation(p_delta);
		m_xform.mul(newXForm);
	}
	
	public void rotate(Vector3 p_axis, double p_angle)
	{
		/* rotate around p_axis, p_angle degrees in counter clockwise */
		
		Transform3D newXForm = new Transform3D();
		newXForm.setRotation(new AxisAngle4d(p_axis, (p_angle * Math.PI / 180)));
		m_xform.mul(newXForm);
	}
	
}
