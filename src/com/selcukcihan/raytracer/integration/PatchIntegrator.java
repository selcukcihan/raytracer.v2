/**
 * @date	: 26.Mar.2009 
 * @author	: selcuk
 * @project	: RayTracer
 * @package	: com.selcukcihan.raytracer.integration
 */
package com.selcukcihan.raytracer.integration;

import javax.vecmath.Vector3f;

import com.selcukcihan.raytracer.Scene;
import com.selcukcihan.raytracer.elements.Material;
import com.selcukcihan.raytracer.elements.PointLight;
import com.selcukcihan.raytracer.math.Point3;
import com.selcukcihan.raytracer.math.Vector3;
import com.selcukcihan.raytracer.vision.Color;
import com.selcukcihan.raytracer.vision.Intersection;
import com.selcukcihan.raytracer.vision.Ray;
import com.selcukcihan.raytracer.vision.Ray.RayType;
import com.selcukcihan.raytracer.vision.sampling.Sample;

/**
 * @author selcuk
 *
 */
public class PatchIntegrator implements IIntegrator
{
	private static final int MAX_RECURSION_DEPTH = 8;
	private int m_recursionDepth = 0;
	
	public Color computeIntensity(Scene p_scene, Ray p_ray, Sample p_sample)
	{
		/*
		 * önce elimizdeki rayi scene ile kesiþtiriyoruz
		 * sonra her light source için
		 * 		shoot a shadow ray from intersection point to light source
		 * 		if(not blocked)
		 * 			color = color + direct illum
		 * 
		 * if(material = reflective)
		 * 		color = color + trace(reflected ray)
		 * if(material = transparent)
		 * 		color = color + trace(refracted)
		 */
		
		Color retVal = new Color(0, 0, 0);
		
		m_recursionDepth++;
		if(m_recursionDepth == MAX_RECURSION_DEPTH)
		{
			m_recursionDepth--;
			return retVal;
		}
			
		Intersection isect = p_scene.getAggregate().intersect(p_ray); /* scene ile ana rayi kesistirdik */
		if(isect == null)
		{
			m_recursionDepth--;
			return retVal;
		}
		
		Material mat = isect.object().getMaterial();
		Vector3 n = isect.normal();
		Vector3 v = p_ray.direction();
		v.scale(-1); /* v artik cisme dogru giden ana ray degil, onun zit yondeki hali */
		
		retVal = retVal.add(mat.AmbientColor); /* ambient color ýþýklardan baðýmsýz olarak ekleniyor en baþta */
		
		for(PointLight light : p_scene.getLights()) /* her noktasal ýþýk kaynaðý için */
		{
			Vector3 shadowDirection = (new Vector3(light.position().sub(isect.point()))).getNormalized();
			//Ray shadowRay = new Ray(isect.point(), shadowDirection, RayType.SHADOW); /* intersectiondan isiga giden ray */
			Ray shadowRay = new Ray(isect.point().shift(Ray.EPSILON, p_ray.direction().mul(-1)), shadowDirection, RayType.SHADOW); /* intersectiondan isiga giden ray */

			Intersection isectShadowRay = p_scene.getAggregate().intersect(shadowRay);
			double distanceSquared = light.position().distanceSquared(isect.point());
			
			if((isectShadowRay == null) || (isectShadowRay.point().distanceSquared(isect.point()) > distanceSquared))
			{ /* shadow ray kimseyi kesmiyorsa (blocklanmamissa) veya kesse bile kestiði nesne ýþýðýn arkasýnda kalýyorsa */
				
				/*
				 * I=Ia*ka+Ip*(kd*(n.L)+ks*((r.v)^ns))
				 * Ia*ka = ambient color, resides in Material.m_ambientColor
				 * Ip = point light intensity, resides in PointLight.m_*Intensity(for r,g,b separately)
				 * kd = diffuse k, resides in Material.m_diffuseK
				 * ks = specular k, resides in Material.m_specularK
				 * ns = specular reflection exponent, resides in Material.m_ns
				 * n = normal vector
				 * L = light vector, looking out from the reflection point
				 * r = reflection vector, looking out from the reflection point
				 * v = viewer vector, towards the viewer 
				 */
				Vector3 L = shadowRay.direction();
				double ndotL = n.dot(L);
				if(ndotL < 0)
					ndotL = 0;
				
				Vector3 incomingFromLight = shadowRay.direction();
				incomingFromLight.scale(-1);
				Vector3 r = isect.reflectedRay(incomingFromLight);

				double rdotv = r.dot(v);
				if(rdotv < 0)
					rdotv = 0;
				if(rdotv > 1)
					rdotv = 1;
				
				retVal = retVal.add(light.Color.
						multiply((mat.DiffuseK.multiply(ndotL)).add((mat.SpecularK.multiply(Math.pow(rdotv, mat.NS)))))); 
			}
		}
		//retVal.multiply((1 - (mat.Reflectivity + mat.Refractivity)));

		/* ana rayin intersectiondan yansimasi */
		Ray reflectedMainRay = isect.reflectedRay(p_ray);
		Color reflectedIntensity = computeIntensity(p_scene, reflectedMainRay, p_sample); 
		retVal = retVal.add(reflectedIntensity.multiply(mat.Reflectivity));
		
		Ray refractedMainRay = isect.refractedRay(p_ray);
		if(refractedMainRay != null)
			retVal = retVal.add(computeIntensity(p_scene, refractedMainRay, p_sample).multiply(mat.Refractivity));
		
		m_recursionDepth--;
		return retVal;
	}

}
