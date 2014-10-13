public class FaceData {

    private String imageID;
    private Node[][] faceData;

    public FaceData(String imageID, Node[][] faceData) {
        this.imageID = imageID;
        this.faceData = faceData;
    }

    public String getImageID() {
        return imageID;
    }

    public Node[][] getFaceData() { return faceData; }
}
