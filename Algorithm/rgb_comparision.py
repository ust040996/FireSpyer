try:

	from PIL import Image

except:

	import Image

import cmath
import math
import sys
import numpy as np
import os

def sorting(fname_sorting):
	with open(fname_sorting) as f:
		sorted_file = sorted(f)
	with open(fname_sorting,'w') as f:
		f.writelines(sorted_file)

def deleteContent(fname):
	with open(fname, "w"):
		pass

def rgb(image1,image2,index_value_of_testimage,index_value_of_trainimage):

	#print (image1)

	#print (image2)

	#print (index_value_of_testimage)

	#print (index_value_of_trainimage)

	image_1 = Image.open(image1);
	image_2 = Image.open(image2);

	pix1 = image_1.load()
	#print (image_1.size)
	pix2 = image_2.load()
	#print (image_2.size)
	x = 0
	y = 0
	rgbdiff = 0
	rgbdiff_r = 0
	rgbdiff_g = 0
	rgbdiff_b = 0
	for x in range(40):
		for y in range(40):
			r1,g1,b1 = pix1[x,y]
			r2,g2,b2 = pix2[x,y]
	
			#obtain the difference in the rgb value of the first two images
			if r1 < r2:
				rgbdiff_r = r2 - r1
			else:
				rgbdiff_r = r1 - r2
			if g1 < g2:
				rgbdiff_g = g2 - g1
			else:
				rgbdiff_g = g1 - g2
			if b1 < b2:
				rgbdiff_b = b2 - b1
			else:
				rgbdiff_b = b1 - b2
			rgbdiff = rgbdiff + rgbdiff_r + rgbdiff_g + rgbdiff_b
	#print (rgbdiff)
	with open('C:\\Users\\UST\\Desktop\\rgbrest\\diff%d.txt' %(index_value_of_testimage),'a+') as mapper_rgb:
		mapper_rgb.write("%d|%d\n" %(rgbdiff,index_value_of_trainimage))


if __name__ == '__main__':

    pathnametrain ='C:\\Users\\UST\\Desktop\\trainimages\\'

    pathnametest ='C:\\Users\\UST\\Desktop\\testimages\\'
    
    if not os.path.exists('C:\\Users\\UST\\Desktop\\rgbrest\\'):

        os.makedirs('C:\\Users\\UST\\Desktop\\rgbrest\\')

    #for i in range(537,10000):
    #	deleteContent('C:\\Users\\UST\\Desktop\\rgbrest\\diff%d.txt' %(i))

    j = 0
    for i in range(537,1000):
    	testPath=os.path.join(os.path.abspath(pathnametest),('%d.png' % i))
    	for j in range(50000):
    		trainPath=os.path.join(os.path.abspath(pathnametrain),('%d.png' %j))
    		rgb(trainPath,testPath,i,j)
    #for i in range(200,10000):
    	sorting('C:\\Users\\UST\\Desktop\\rgbrest\\diff%d.txt' %(i))
