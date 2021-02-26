package py.com.sigati.bean;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.PostConstruct;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

@ManagedBean
@ApplicationScoped
public class AppVersionView {
	private static final Logger LOGGER = LogManager.getLogger(AppVersionView.class);

	private String version;
	private String jdkBuild;
	private String buildTime;
	private String buildBy;
	private String createBy;



	@PostConstruct
	private void init(){
		Properties properties = loadManifestFile();
		jdkBuild = properties.getProperty("Build-Jdk");
		version = properties.getProperty("version");
		buildTime = properties.getProperty("buildTime");
		buildBy = properties.getProperty("Built-By");
		createBy = properties.getProperty("Created-By");
	}


	private Properties loadManifestFile() {
		ServletContext servletContext = (ServletContext) FacesContext
				.getCurrentInstance().getExternalContext().getContext();
		Properties prop = new Properties();
		try {
			InputStream resourceAsStream = servletContext.getResourceAsStream("/META-INF/MANIFEST.MF");
			if (resourceAsStream != null) {
				prop.load(resourceAsStream);        }
		} catch (IOException e) {
			LOGGER.error("Se produjo un error al obtener datos de version", e);
		}
		return prop;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getBuildTime() {
		return buildTime;
	}

	public void setBuildTime(String buildTime) {
		this.buildTime = buildTime;
	}

	public String getJdkBuild() {
		return jdkBuild;
	}

	public void setJdkBuild(String jdkBuild) {
		this.jdkBuild = jdkBuild;
	}

	public String getBuildBy() {
		return buildBy;
	}

	public void setBuildBy(String buildBy) {
		this.buildBy = buildBy;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}
}
