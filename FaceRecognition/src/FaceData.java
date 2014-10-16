/**
 * FaceData: Class that is a data carrier for a Face image, 
 * stores each pixel value in a matrix of integers.
 * also stores the image id as a string.
 */
public class FaceData {

    private String imageID;
    private int[][] faceData;

    /**
     * Constructor
     * 
     * @param imageID
     * @param faceData
     */
    public FaceData(String imageID, int[][] faceData) {
        this.imageID = imageID;
        this.faceData = faceData;
    }

    /**
     * Getter for imageID
     * 
     * @return imageID
     */
    public String getImageID() {
        return imageID;
    }

    /**
     * Getter for faceData.
     * 
     * @return FaceData
     */
    public int[][] getFaceData() { return faceData; }
}
