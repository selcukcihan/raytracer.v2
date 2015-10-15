package com.selcukcihan.raytracer;

import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;

import javax.media.j3d.Transform3D;
import javax.swing.JFrame;
import javax.vecmath.Point3d;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector3f;

import com.selcukcihan.raytracer.Scene;
import com.selcukcihan.raytracer.elements.Drawable;
import com.selcukcihan.raytracer.elements.Material;
import com.selcukcihan.raytracer.elements.PointLight;
import com.selcukcihan.raytracer.elements.Sphere;
import com.selcukcihan.raytracer.integration.IIntegrator;
import com.selcukcihan.raytracer.integration.PatchIntegrator;
import com.selcukcihan.raytracer.math.Point3;
import com.selcukcihan.raytracer.math.Vector3;
import com.selcukcihan.raytracer.vision.Camera;
import com.selcukcihan.raytracer.vision.Color;
import com.selcukcihan.raytracer.vision.Film;
import com.selcukcihan.raytracer.vision.sampling.TrivialSampler;

/**
 * @date	: 30.Mar.2009 
 * @author	: selcuk
 * @project	: RayTracer
 * @package	: 
 */

/**
 * @author selcuk
 *
 */
public class Test extends JFrame
{
	private Scene m_scene = null;
	private Camera m_cam = null;
	private TrivialSampler m_sampler = null;
	private IIntegrator m_integrator = null;
	private BufferedImage m_img = null;
	
	public final static int HEIGHT = 800;
	public final static int WIDTH = 1280;
	
	private Material m_matReflective =
		new Material(new Color(0.1, 0.1, 0.1), new Color(0.1), new Color(1, 1, 1), 128, new Color(1, 1, 1), new Color(0.0), 1.5);
	
	private Material m_matDiffuse =
		new Material(new Color(0.02, 0.16, 0.41), new Color(1, 1, 1), new Color(0), 64, new Color(0), new Color(0.0), 1.5);
	
	private Material m_matShiny =
		new Material(new Color(0.66, 0.02, 0.02), new Color(0.4), new Color(1, 1, 1), 128, new Color(1, 1, 1), new Color(0.0), 1.5);
	
	private Material m_matRefractive =
		new Material(new Color(0.66, 0.02, 0.02), new Color(0.4), new Color(1, 1, 1), 128, new Color(1, 1, 1), new Color(1), 1.03);

	public void testSphere4()
	{
		//PointLight light0 = m_scene.addLight(new PointLight());
		//light0.translate(new Vector3(0, 0, 0));
		
		PointLight light1 = m_scene.addLight(new PointLight());
		light1.translate(new Vector3(0, 0, 10000));
		
		Drawable sphere = m_scene.addObject(new Sphere(200), m_matRefractive);
		sphere.translate(new Vector3(0, 0, 0));
		
		Drawable s1 = m_scene.addObject(new Sphere(100), m_matReflective);
		s1.translate(new Vector3(-200, 0, -400));
		
		Drawable s2 = m_scene.addObject(new Sphere(100), m_matReflective);
		s2.translate(new Vector3(200, 0, -400));
		
		int frontPlane = 100000;
		m_cam = new Camera(frontPlane, WIDTH, HEIGHT);
		m_cam.lookAt(new Point3d(00, 0, 100000), new Point3d(0, 0, 0),
				new Vector3d(0, 1, 0));
	}
	
	public void testSphere3()
	{
		//PointLight light0 = m_scene.addLight(new PointLight());
		//light0.translate(new Vector3(0, 0, 0));
		
		PointLight light1 = m_scene.addLight(new PointLight());
		light1.translate(new Vector3(0, 0, 10000));
		
		Drawable sphere = m_scene.addObject(new Sphere(200), m_matRefractive);
		sphere.translate(new Vector3(0, 0, 0));
		
		Drawable s = m_scene.addObject(new Sphere(100), m_matDiffuse);
		s.translate(new Vector3(0, 0, -800));
		
		int frontPlane = 100000;
		m_cam = new Camera(frontPlane, WIDTH, HEIGHT);
		m_cam.lookAt(new Point3d(-30000, 0, 100000), new Point3d(0, 0, 0),
				new Vector3d(0, 1, 0));
	}
	
	public void testSphere()
	{
		//PointLight light0 = m_scene.addLight(new PointLight());
		//light0.translate(new Vector3(0, 0, 0));
		
		PointLight light1 = m_scene.addLight(new PointLight());
		light1.translate(new Vector3(0, 0, 10000));
		
		Drawable sphere = m_scene.addObject(new Sphere(200), m_matRefractive);
		sphere.translate(new Vector3(0, 0, 0));
		
		for(double a = 0; a <= 180; a += 90)
		{
			for(double b = 0; b < 360; b += 90)
			{
				Vector3 point = new Vector3(350*Math.sin(a*Math.PI/180)*Math.cos(b*Math.PI/180),
						350*Math.sin(a*Math.PI/180)*Math.sin(b*Math.PI/180),
						350*Math.cos(a*Math.PI/180));
				
				Drawable s = m_scene.addObject(new Sphere(75), m_matDiffuse);
				s.translate(point);
			}
		}
		
		int frontPlane = 100000;
		m_cam = new Camera(frontPlane, WIDTH, HEIGHT);
		m_cam.lookAt(new Point3d(-50000, 0, 100000), new Point3d(0, 0, 0),
				new Vector3d(0, 1, 0));
	}
	
	public void testSphere1()
	{
		PointLight light0 = m_scene.addLight(new PointLight());
		light0.translate(new Vector3(900, 300, -1500));
		
		PointLight light1 = m_scene.addLight(new PointLight());
		light1.translate(new Vector3(10000, -1000, 0));
		
		PointLight light2 = m_scene.addLight(new PointLight());
		light2.translate(new Vector3(-300, -300, 1500));
		
		//Material lightMat = new Material(new Color(1.0, 1.0, 1.0), new Color(1), new Color(0), 64, new Color(0), new Color(0), 1.0f);
		//Drawable lightSphere = m_scene.addObject(new Sphere(4), lightMat);
		//lightSphere.translate(new Vector3(-300, -300, 1510));
		
		//Drawable sphere0 = m_scene.addObject(new Sphere(50), mat0); 
		//sphere0.translate(new Vector3(0, 200, 200));
		
		Drawable sphere1 = m_scene.addObject(new Sphere(100), m_matReflective);
		sphere1.translate(new Vector3(-400, -150, 0));
		
		Drawable sphere2 = m_scene.addObject(new Sphere(100), m_matDiffuse);
		sphere2.translate(new Vector3(-200, 150, 0));
		
		Drawable sphere3 = m_scene.addObject(new Sphere(100), m_matShiny);
		sphere3.translate(new Vector3(0, 250, 0));
		
		Drawable sphere4 = m_scene.addObject(new Sphere(100), m_matDiffuse);
		sphere4.translate(new Vector3(350, 150, 0));
		
		Drawable sphere5 = m_scene.addObject(new Sphere(100), m_matReflective);
		sphere5.translate(new Vector3(400, -150, 0));
		
		int frontPlane = 100000;
		m_cam = new Camera(frontPlane, WIDTH, HEIGHT);
		m_cam.lookAt(new Point3d(0, 0, 100000), new Point3d(0, 0, 0),
				new Vector3d(0, 1, 0));
	}
	
	public void testSphere2()
	{
//		PointLight light0 = m_scene.addLight(new PointLight());
//		light0.translate(new Vector3(0, 0, 0));
//		
//		Drawable lightSphere = m_scene.addObject(new Sphere(4), new Material(new Color(1.0f, 1.0f, 1.0f), 0.3f, 0f, 64, 0.0f, 0.0f, 1.0f));
//		lightSphere.translate(new Vector3(0, 0, -10));
//		
//		
//		Material mat0 = new Material(new Color(0.1f, 0.1f, 0.1f), 0.0f, 0.0f, 64, 1.0f, 0.0f, 1.5f);
//		Material mat1 = new Material(new Color(0.1f, 1.0f, 0.9f), 0.6f, 0.7f, 64, 0.0f, 0.0f, 1.5f);
//		Material mat2 = new Material(new Color(0.8f, 0.5f, 0.2f), 0.6f, 0.5f, 64, 0.5f, 0.0f, 1.5f);
//		
//		Drawable sphere0 = m_scene.addObject(new Sphere(5000), mat0); 
//		sphere0.translate(new Vector3(0, 5500, 0));
//		
//		Drawable sphere1 = m_scene.addObject(new Sphere(100), mat1);
//		sphere1.translate(new Vector3(0, -200, 0));
//		
//		Drawable sphere2 = m_scene.addObject(new Sphere(150), mat2);
//		sphere2.translate(new Vector3(-0, -500, 0));
//		
//		int frontPlane = 500;
//		m_cam = new Camera(frontPlane);
//		m_cam.lookAt(new Point3d(-900, -400, 0), new Point3d(0, 0, 0),
//				new Vector3d(0, 0, 1));
	}
	
	public Test()
	{
		super("raytracer v1");
		super.addWindowListener(new WindowAdapter(){
			public void windowClosing(WindowEvent e)
			{
				e.getWindow().dispose();
			}
		});
		
		m_scene = new Scene();
		
		//m_light2 = m_scene.addLight(new PointLight(new Color(1f, 1f, 1f)));
		//m_light2.translate(new Vector3(-2000, -2000, 0));
		testSphere4();
		
		m_sampler = new TrivialSampler(WIDTH, HEIGHT);
		
		m_integrator = new PatchIntegrator();
		m_scene.setIntegrator(m_integrator);
		
		m_scene.render(m_sampler, m_cam);
		
		drawFromFilm(m_cam.getFilm());
		
		m_img = m_cam.getFilm().getImage();
		
		this.setLocation(0, 0);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setVisible(true);
	}
	
	public void paint(Graphics g)
	{
		super.paint(g);
		g.drawImage(m_img, 0, 0, WIDTH, HEIGHT, null);
	}

	/**
	 * @param p_film
	 */
	private void drawFromFilm(Film p_film)
	{
		// TODO Auto-generated method stub
		p_film.toFile("raytraced");
	}

	/**
	 * @param args
	 */
	public static void main(String[] args)
	{
		Point3d p1 = new Point3d(0, 0, 0);
		Point3d p2 = new Point3d(0, -100, -100);
		Transform3D xform = new Transform3D();
		xform.lookAt(new Point3d(0, 0, 500), new Point3d(0, 1, 500), new Vector3d(0, 0, 1));
		xform.invert();
		
		xform.transform(p1);
		xform.transform(p2);
		
		
		new Test();
	}

}
