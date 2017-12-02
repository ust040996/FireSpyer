# -*- coding: utf-8 -*-
"""
Created on Sat Dec  2 09:54:01 2017

@author: UST
"""
import os
try:
    from PIL import Image
except:
    import Image

def sorting(fname_sorting):
	with open(fname_sorting) as f:
		sorted_file = sorted(f)
	with open(fname_sorting,'w') as f:
		f.writelines(sorted_file)

def deleteContent(fname):
	with open(fname, "w"):
		pass


def jpegtopng(test,train):
    for il in test[:]:
        i = 0
        if not(il.endswith(".png")):
            testPath=os.path.join(os.path.abspath(test),('%d.jpg' % i))
        else:
            testPath=os.path.join(os.path.abspath(test),('%d.png' % i))
            im = Image.open(testPath)
            im.save('%d.png' % i)
            print('1')
            im.close()
        i += 1
    for il in train[:]:
        i = 0
        if not(il.endswith(".png")):
            trainPath=os.path.join(os.path.abspath(train),('%d.jpg' % i))
        else:
            trainPath=os.path.join(os.path.abspath(train),('%d.png' % i))
            im = Image.open(trainPath)
            im.save('%d.png' % i)
            print('1')
            im.close()
        i += 1
            
def look(omage1,omage2,index_value_of_testimage,index_value_of_trainimage):
    size = 50,50;
    image1 = Image.open(omage1);
    image1.close();
    if image1.size != size:
        print(2)
        try:
            image1.thumbnail(size, Image.ANTIALIAS)
            image1.save(os.path.join(os.path.abspath(omage1),('%d.jpg' % index_value_of_trainimage)))
        except IOError:
            print ("File error ")
    image2 = Image.open(omage2);
    image2.close();         
    if image2.size != size:
        try:
            image2.thumbnail(size, Image.ANTIALIAS)
            image2.save(os.path.join(os.path.abspath(omage2),('%d.jpg' % index_value_of_testimage)))
        except IOError:
            print ("File error ")
def rgb(Image1,Image2,index_value_of_testimage,index_value_of_trainimage):
    image1 = Image.open(Image1);
    image1.close();
    image2 = Image.open(Image2);
    image2.close();
    pix1 = image1.load()
    pix2 = image2.load()
    print(image1.size)
    x=0
    y=0
    rgbdiff = 0
    rgbdiff_r = 0
    rgbdiff_g = 0
    rgbdiff_b = 0
    for x in range(0,30):
        for y in range(0,30):
            r1, g1, b1 = pix1[x,y]
            r2, g2, b2 = pix2[x,y]
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
    with open('C:\\Users\\UST\\Desktop\\rgb\\diff%d.txt' %(index_value_of_testimage),'a+') as mapper_rgb:
        mapper_rgb.write("%d|%d\n" %(rgbdiff,index_value_of_trainimage))
    

if __name__=='__main__':
    pathnametest ='C:\\Users\\UST\\Desktop\\dataset\\test'
    pathnametrain ='C:\\Users\\UST\Desktop\\dataset\\train'
    jpegtopng(pathnametest,pathnametrain)
    if not os.path.exists('C:\\Users\\UST\\Desktop\\dataset\\rgb'):
        os.makedirs('C:\\Users\\UST\\Desktop\\dataset\\rgb')
    for i in range(0,25):
        testPath=os.path.join(os.path.abspath(pathnametest),('%d.png' % i))
        for j in range(0,80):
            trainPath = os.path.join(os.path.abspath(pathnametrain),('%d.png' % j))
            look(pathnametest,pathnametrain,i,j)
            rgb(trainPath,testPath,i,j)