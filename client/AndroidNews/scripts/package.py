  # coding=utf-8
import sys
import os



file = '..'
paths = ['../src', '../kit', '../widget', '../http','../test']
respath = '../res'
manifestpath = '../AndroidManifest.xml'
oldPack = 'app.yhpl.news'
newPack = 'news.yhpl.app'

oldR = 'import ' + oldPack + '.R;' + '\n'
newR = 'import ' + newPack + '.R;' + '\n'
oldBuildCfg = 'import ' + oldPack + '.BuildConfig;' + '\n'
newBuildCfg = 'import ' + newPack + '.BuildConfig;' + '\n'

providerPrefix = 'android:authorities'
applicationPackage = "package="

oldLayoutNs = r'http://schemas.android.com/apk/res/app.yhpl.news'
newLayoutNs = r'http://schemas.android.com/apk/res-auto'


def outJavaFiles():
    oldPackNameArray = oldPack.split('.')
    for childPath in paths:
        print childPath
        for parent, direction, files in os.walk(childPath):
            for file in files:
                filePath = '' + parent + '\\' + file
                # print('filePath:'+filePath)
                if parent.split('\\')[-3:] == oldPackNameArray:
                     fileAddRUnderOldPackage(filePath) 
                else:
                     fileRReplace(filePath)
def fileAddRUnderOldPackage(file):
    print file
    input = open(file)
    lines = input.readlines()
    input.close()
    output = open(file, 'w')
    index = 0;
    for line in lines:
        output.write(line)
        if index < 2:
            index += 1
            if index == 2:
                output.write(newR)    

def fileRReplace(file):
    input = open(file)
    lines = input.readlines()
    input.close()
    output = open(file, 'w')
    for line in lines:
        if line == oldR:
            output.write(newR)
        elif line == oldBuildCfg:
            output.write(newBuildCfg)
        else:
            output.write(line)

def fileRMainfest():
    input = open(manifestpath)
    lines = input.readlines()
    input.close()
    output = open(manifestpath, 'w')
    find = False
    for line in lines:
        if not find and  line.find(applicationPackage) > -1 and line.find(oldPack) > -1:
            newLine = line.replace(oldPack, newPack)
            output.write(newLine)
            print 'replace androidManifest.xml packagename'
            find = True
        elif line.find(providerPrefix) > -1:
            newLine = line.replace(oldPack, newPack)
            output.write(newLine)
            print 'replace androidManifest.xml provider'
        else:
            output.write(line)

def outLayoutFiles():
    abspath = os.path.abspath(respath)
    print abspath
    for parent, direction, files in os.walk(abspath):
        for d in direction:
            if not d:
                continue
            elif d.find('layout') > -1:
                print 'find layout'
                for layoutParent, layoutFolder, layoutFiles in os.walk(parent + '\\' + d):
                    for layoutFile in layoutFiles:
                        layoutFilesReplace(layoutParent + '\\' + layoutFile)
    

def layoutFilesReplace(file):
    input = open(file)
    lines = input.readlines()
    input.close()
    output = open(file, 'w')
    find = False;
    for line in lines:
        if line.find(oldLayoutNs) > -1:
            if not find:
                find = True
                print 'replace namespace in layout file ', file
            newLine = line.replace(oldLayoutNs, newLayoutNs)
            output.write(newLine)
        else:
            # print '```'
            output.write(line)

if __name__ == '__main__':
    outJavaFiles()
    fileRMainfest()
    outLayoutFiles()
