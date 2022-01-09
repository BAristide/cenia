package net.linksguard.models;
import org.springframework.web.multipart.MultipartFile;
import java.util.Arrays;
public class UploadModel {

    private String projectLabel;
    private String processionPriority;
    private String shortLabel;
    private String descriptionLabel;
    private String shortCauseLabel;
    private String causeLabel;

    private MultipartFile[] files;

     

    public String getProjectLabel() {
		return projectLabel;
	}

	public void setProjectLabel(String projectLabel) {
		this.projectLabel = projectLabel;
	}

	public String getProcessionPriority() {
		return processionPriority;
	}

	public void setProcessionPriority(String processionPriority) {
		this.processionPriority = processionPriority;
	}

	public String getShortLabel() {
		return shortLabel;
	}

	public void setShortLabel(String shortLabel) {
		this.shortLabel = shortLabel;
	}

	public String getDescriptionLabel() {
		return descriptionLabel;
	}

	public void setDescriptionLabel(String descriptionLabel) {
		this.descriptionLabel = descriptionLabel;
	}

	public String getShortCauseLabel() {
		return shortCauseLabel;
	}

	public void setShortCauseLabel(String shortCauseLabel) {
		this.shortCauseLabel = shortCauseLabel;
	}

	public String getCauseLabel() {
		return causeLabel;
	}

	public void setCauseLabel(String causeLabel) {
		this.causeLabel = causeLabel;
	}

	public MultipartFile[] getFiles() {
        return files;
    }

    public void setFiles(MultipartFile[] files) {
        this.files = files;
    }

    @Override
    public String toString() {
        return "UploadModel{" +
                "projectLabel='" + projectLabel + '\'' +
                ",processionPriority='" + processionPriority + '\'' +
                ",shortLabel='" + shortLabel + '\'' +
                ",descriptionLabel='" + descriptionLabel + '\'' +
                ",shortCauseLabel='" + shortCauseLabel + '\'' +
				",causeLabel='" + causeLabel + '\'' +
                ", files=" + Arrays.toString(files) +
                '}';
    }
}