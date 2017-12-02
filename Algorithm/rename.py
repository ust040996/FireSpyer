import os

if __name__ == '__main__':
     path ="C:\Users\UST\Desktop\data1"
     files=os.listdir(path)
     for i in range(len(files)):
         print(files[i])
         os.rename(os.path.join(os.path.abspath(path),files[i]),path+'MS_'+files[i])