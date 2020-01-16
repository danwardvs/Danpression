# Danpression
A compression algorithm and image file format that stores images that are highly compressed and have a very small file size.

### How it works:
The image is loaded into memory and 7 central pixels are chosen as samples. The algorithm then finds what sample pixel each pixel of the image is closest to. It then stores the pixel as a value to 1-7 referencing to what source pixel it is. These values are written to a binary file with the 7 source pixel's values and the image's dimenensions as the header of the file. This file is then 7zipped and stored as a .dan image. Viewing is same steps in reverse order.

### Results
Source image:
![Alt text](../master/demo/tree.jpg)

becomes this when its saved as a .dan file:
![Alt text](../master/demo/tree_converted.jpg)


Let's look at the file sizes:

| Image       | Size(kb)         | 
| ------------- |-------------|
| tree.bmp      | 2214.6 |
| tree.png      | 960.6   |  
| tree.jpg      | 163.8   |
| tree.dan |  75.4 |

Wow! The .dan image is less than half the .jpg size, or 3.4% of the size of the .bmp image. That's a compact format, and it's pretty close* to the source image.

Here's a couple more demos:

![Alt text](../master/demo/track.jpg)
![Alt text](../master/demo/track_converted.jpg)


![Alt text](../master/demo/tiger.jpg)
![Alt text](../master/demo/tiger_converted.jpg)




### Disclaimer
Yes, I know this is a terrible image format and is not meant for any serious use. It was fun to make and it does work to
produce a very small image file. You can probably get better results using a low colour depth .png file.

 ### Build deps:
* [SevenZip JBinding](http://sevenzipjbind.sourceforge.net/). Requires libs sevenzipjbinding.jar and sevenzipjbinding-PLATFORM.jar.
Can be found in /libs directory of [archives found here.](https://sourceforge.net/projects/sevenzipjbind/files/7-Zip-JBinding/9.20-2.00beta/)
* Swing GUI

### Usage: 
* Run Danpression.jar with an up-to-date JRE installation. `java -jar Danpression.jar`To quickly view an image, launch with `view /path/to/image.dan` arguments. Builds can be found on the release tab
