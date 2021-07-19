import cv2
import glob
import os
nameList = []
w=0
b=0

for i in glob.glob("*.jpg"):
    print(i)
    img = cv2.imread(i)
    cv2.imwrite("img"+str(b)+".jpg", img)
    b+=1
   
