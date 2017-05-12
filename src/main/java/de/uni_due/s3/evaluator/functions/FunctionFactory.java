package de.uni_due.s3.evaluator.functions;

import java.io.File;
import java.io.FilenameFilter;
import java.net.URL;
import java.util.HashMap;


/**
 * This class will be used to load a specified function dynamically.
 * Therefore it implements the FactoryPattern to return the specified class and 
 * implements the [initialization-on-demand holder design pattern](https://en.wikipedia.org/wiki/Initialization-on-demand_holder_idiom), too, so that there is no problem with concurrency initializing this class
 * When the instance of this class will be created, it loads all function's name from all sub packages.
 * If the creation of a function is requested, then the method will retrieve the location of the package in which the function should be
 * and load then load the function.
 * 
 * @author frichtscheid
 *
 */
public class FunctionFactory {
	private HashMap<String,String> classPackages;
	
	/**
	 * Initialize the Factory by searching all sub packages of this package for function ('*.class' -files)
	 * and saves the names of all founded functions.
	 */
    private FunctionFactory(){
    	//get the context of the current location and transform it to a path
    	ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
    	String packageName = this.getClass().getPackage().getName().replace(".", "/");
    	
    	//get all files in this path
    	URL path = classLoader.getResource(packageName);
    	File folder = new File(path.getPath());
    	classPackages = new HashMap<String,String>();
    	
    	//search for packages ( folders ) and extract all class files from each package
    	for(File packageFile:folder.listFiles()){
    		if(packageFile.isDirectory()){
    			//load class filenames
    			System.out.println(packageFile.getName());
    			File packageFolder = new File(packageFile.getAbsolutePath());
    			FilenameFilter simpleClassFilter = new FilenameFilter() {
					
					@Override
					public boolean accept(File dir, String name) {
						// TODO Auto-generated method stub
						return (name.endsWith(".class"));
					}
				};
    			
				//save the founded class files for this package
    			for(String clazz:packageFolder.list(simpleClassFilter)) {
    				//removing .class from clazz-name and save it
    				classPackages.put(clazz.substring(0, clazz.length()-6), packageFile.getName());
    			}
    		}
    	}
    }
    
    /**
     * This makes the class a thread-safe Singleton
     * see [initialization-on-demand holder design pattern](https://en.wikipedia.org/wiki/Initialization-on-demand_holder_idiom)
     * @author frichtscheid
     *
     */
    private static class LazyHolder {
    	static final FunctionFactory INSTANCE = new FunctionFactory();
    }
    
    public static FunctionFactory getInstance(){
    	return LazyHolder.INSTANCE;
    }
    
    /**
     * Instantiate a function by its name.
     * If the name of the function was not found in any sub packages it will throw an error
     * @param name the name of the function which should be instantiated
     * @return A new Instance of the function
     * @throws Exception 
     */
	public IFunction createFunction(String name) throws Exception{
		IFunction instance = null;
		
		//Every class starts with a big char ...
		name = capitalizeFirstChar(name);
		
		//check in which package the function should be
		String packageName = this.classPackages.get(name);
		if(packageName == null){
			//throw an error
			throw new Exception();
		}else {
			//load File
			try {
				//chaining the found packageName to the name of this package
				packageName = this.getClass().getPackage().getName()+"."+packageName;
				instance = (IFunction) Class.forName(packageName+"."+name).newInstance();

				//TODO: There should be an error handling
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}catch(ClassCastException e){
				//throw FunctionDoesNotExistsError?
				e.printStackTrace();
			} catch (InstantiationException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
		return instance;
	}
	
	/**
	 * Transforms the first char into a big char
	 * @param name 
	 * @return modified name
	 */
	private static String capitalizeFirstChar(String name){
		return name.substring(0, 1).toUpperCase()+name.substring(1);
	}
	
}
