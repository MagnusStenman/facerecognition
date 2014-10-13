public class FaceData {

    private String imageID;
    private int[][] faceData;

    public FaceData(String imageID, int[][] faceData) {
        this.imageID = imageID;
        this.faceData = faceData;
    }

    public String getImageID() {
        return imageID;
    }

    public int[][] getFaceData() { return faceData; }
}
