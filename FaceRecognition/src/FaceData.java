
public class FaceData {

    private String imageID;
    private Integer[][] faceData;

    public FaceData(String imageID, Integer[][] faceData) {
        this.imageID = imageID;
        this.faceData = faceData;
    }

    public String getImageID() {
        return imageID;
    }

    public Integer[][] getFaceData() {
        return faceData;
    }
}
