package com.oryzone.mvdetector;

import static com.googlecode.javacv.cpp.opencv_core.*;
import static com.googlecode.javacv.cpp.opencv_imgproc.CV_BGR2GRAY;
import static com.googlecode.javacv.cpp.opencv_imgproc.cvCvtColor;
import static com.googlecode.javacv.cpp.opencv_objdetect.cvHaarDetectObjects;
import com.googlecode.javacv.cpp.opencv_core.IplImage;
import com.googlecode.javacv.cpp.opencv_objdetect.CvHaarClassifierCascade;
import java.io.File;


/**
 * Class used to track faces in <IplImage> instances
 * @author Luciano Mammino, Andrea Mangano
 */
public class FaceDetector
{
    
    /**
     * The file that defines the classifier features
     */
    protected final String CASCADE_FILE = "haarcascade_frontalface_alt.xml";
    
    /**
     * The memory storage
     */
    protected CvMemStorage storage;
    
    /**
     * The classifier used to detect faces
     */
    protected CvHaarClassifierCascade cascade;
    
    
    /**
     * The color used to draw the contour of the tracked faces
     */
    protected CvScalar borderColor;
    
    
    /**
     * Constructor. Creates a new instance of FaceDetector
     */
    public FaceDetector()
    {
        this.storage = CvMemStorage.create();
        this.cascade = new CvHaarClassifierCascade( cvLoad(getClass().getResource("/res/" + CASCADE_FILE).getPath()) );
        this.borderColor = CvScalar.YELLOW;
    }

    
    /**
     * Gets the border color
     * @return 
     */
    public CvScalar getBorderColor()
    {
        return borderColor;
    }


    /**
     * Sets the border color
     * @param color
     * @return The same instance for fluent syntax
     */
    public FaceDetector setBorderColor(CvScalar color)
    {
        this.borderColor = color;
        return this;
    }
    
    
    /**
     * Detect faces on an image
     * @param source the image to track
     * @return a CvSeq containing all the detected faces
     */
    public CvSeq detect(IplImage source)
    {
        IplImage grayImage = IplImage.create(source.width(),
                                            source.height(), IPL_DEPTH_8U, 1);
        cvCvtColor(source, grayImage, CV_BGR2GRAY);
        return cvHaarDetectObjects(grayImage, cascade, storage, 1.1, 1,0);
    }
    
    
    /**
     * Detect the faces on a source image and draws a rectangle around them
     * @param source the source image
     * @return the resulting annoted image
     */
    public IplImage detectAndDraw(IplImage source)
    {
        IplImage result = source.clone();
        CvSeq faces = this.detect(source);
        
        for (int i = 0; i < faces.total(); i++)
        {
            CvRect r = new CvRect(cvGetSeqElem(faces, i));
            cvRectangle(result, cvPoint(r.x(), r.y()),
                cvPoint(r.x() + r.width(), r.y() + r.height()), this.borderColor, 1,
                CV_AA, 0);
        }
        
        return result;
    }
    
}


//TODO: remove this lines
/*
 * 
 * public class FaceDetection {

// The cascade definition to be used for detection.
private static final String CASCADE_FILE = "haarcascade_frontalface_alt.xml";

public static void main(String[] args) throws Exception {

// Load the original image.
IplImage originalImage = cvLoadImage(args[0], 1);

// We need a grayscale image in order to do the recognition, so we
// create a new image of the same size as the original one.
IplImage grayImage = IplImage.create(originalImage.width,
originalImage.height, IPL_DEPTH_8U, 1);

// We convert the original image to grayscale.
cvCvtColor(originalImage, grayImage, CV_BGR2GRAY);

CvMemStorage storage = CvMemStorage.create();

// We instantiate a classifier cascade to be used for detection, using the cascade definition.
CvHaarClassifierCascade cascade = new CvHaarClassifierCascade(
cvLoad(CASCADE_FILE));

// We detect the faces.
CvSeq faces = cvHaarDetectObjects(grayImage, cascade, storage, 1.1, 1,
0);

//We iterate over the discovered faces and draw yellow rectangles around them.
for (int i = 0; i < faces.total; i++) {
CvRect r = new CvRect(cvGetSeqElem(faces, i));
cvRectangle(originalImage, cvPoint(r.x, r.y),
cvPoint(r.x + r.width, r.y + r.height), CvScalar.YELLOW, 1,
CV_AA, 0);
}

// Save the image to a new file.
cvSaveImage(args[1], originalImage);
}
}
 */
