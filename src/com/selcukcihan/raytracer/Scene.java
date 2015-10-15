/**
 * @date	: 24.Mar.2009 
 * @author	: selcuk
 * @project	: RayTracer
 * @package	: com.selcukcihan.raytracer
 */
package com.selcukcihan.raytracer;

import java.util.LinkedList;

import com.selcukcihan.raytracer.elements.Aggregate;
import com.selcukcihan.raytracer.elements.Drawable;
import com.selcukcihan.raytracer.elements.IGeometry;
import com.selcukcihan.raytracer.elements.IPrimitive;
import com.selcukcihan.raytracer.elements.Material;
import com.selcukcihan.raytracer.integration.IIntegrator;
import com.selcukcihan.raytracer.vision.Camera;
import com.selcukcihan.raytracer.vision.Color;
import com.selcukcihan.raytracer.vision.Ray;
import com.selcukcihan.raytracer.vision.sampling.ISampler;
import com.selcukcihan.raytracer.vision.sampling.Sample;
import com.selcukcihan.raytracer.elements.PointLight;

/**
 * @author selcuk
 *
 */
public class Scene
{
	private Aggregate m_agg = null;
	private LinkedList<PointLight> m_lights = null;
	private Camera m_cam = null;
	private IIntegrator m_integrator = null;
	private ISampler m_sampler = null;
	
	public Scene()
	{
		m_lights = new LinkedList<PointLight>();
	}
	
	public Camera setCamera(Camera p_cam)
	{
		m_cam = p_cam;
		return m_cam;
	}
	public Camera getCamera() { return m_cam; }
	
	public ISampler setSampler(ISampler p_sampler)
	{
		m_sampler = p_sampler;
		return m_sampler;
	}
	public ISampler getSampler() { return m_sampler; }
	/*
	public IPrimitive setAggregate(IPrimitive p_agg)
	{
		m_agg = p_agg;
		return m_agg;
	}
	*/
	public IPrimitive getAggregate() { return m_agg; }
	
	public IIntegrator setIntegrator(IIntegrator p_integrator)
	{
		m_integrator = p_integrator;
		return m_integrator;
	}
	public IIntegrator getIntegrator() { return m_integrator; }
	
	public PointLight addLight(PointLight p_light)
	{
		m_lights.add(p_light);
		return p_light;
	}
	
	public LinkedList<PointLight> getLights() { return m_lights; }
	
	public Drawable addObject(IGeometry p_geo, Material p_mat)
	{
		Drawable d = new Drawable(p_geo, p_mat);
		if(m_agg == null)
			m_agg = new Aggregate();
		m_agg.add(d);
		return d;
	}
	
	public void preProcess()
	{
		/* aggregate hiyerarþisi oluþturulacak */
	}
	
	public boolean render(ISampler p_sampler, Camera p_cam)
	{
		preProcess();
		
		setCamera(p_cam);
		setSampler(p_sampler);
		
		if((m_sampler == null) || (m_cam == null) || (m_agg == null) ||
				(m_integrator == null))
		{
			return false;
		}
		
		Sample sample = null;
		while((sample = m_sampler.nextSample()) != null)
		{
			Ray ray = m_cam.generateRay(sample);
			Color color = computeIntensity(ray, sample);
			sample.imgY = Test.HEIGHT - sample.imgY;
			m_cam.getFilm().addSample(sample, ray, color);
		}

		return true;
	}

	/**
	 * @param p_ray
	 * @param p_sample
	 * @return
	 */
	private Color computeIntensity(Ray p_ray, Sample p_sample)
	{
		return m_integrator.computeIntensity(this, p_ray, p_sample);
	}
}
