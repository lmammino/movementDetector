Movemente Detector
==================
Movement Detector is a simple Java OpenCv application created as university project for the computer vision course at La sapienza, Rome.
It aims to detect when movements occur in front of a camera by using pixel based frame difference.
It also provides the ability to detect faces when a moderate quantity of movement is detected.

Requirements
------------
  * Java JRE 1.6+
  * OpenCV 2.2+
  * a webcam
(The other required libraries are shipped with Movement Detector in the `dist/lib/` folder)

Running
-------
You can find a runnable jar (last updated version) in the `dist` folder.<br/>
To run it simply use: `java -jar "movementDetector.jar"`.<br/>
On some OSs you can simply run the app by clicking on the movementDetector.jar file.

*NB:* do not move or remove the `lib` folder

Known Issues
------------
If you use Movement Detector on Mac you may experience problems when starting the camera. Sometimes the application may get stuck and you have to force close and restart it again. It seems due to an OpenCV bug that does not handle threads in a safe way on mac. The problem will be resolved when openCV authors will release a fixed version. So we apparently have to wait. In the meanwhile any workaround would be heavily appreciated. So, please, contact us if you know some.
