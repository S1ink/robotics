import os
import time
import shutil
from datetime import datetime
import csv
import pandas as pd

default_csv = 'project_import.csv'

def now():
    return datetime.now()

def dircheck(path:str):
    if os.path.exists(path):
        return True
    elif os.path.exists(path) == False:
        return False
    else:
        return "Error"

def lastmod(item:str=None):
    '''
    gets last modified time of folder or file
    '''
    return time.ctime(os.path.getmtime(item))

def lastmod_s(item:str=None):
    '''
    gets the last modified time and does not convert from seconds
    '''
    return os.path.getmtime(item)


def syncfolders(csv:str=default_csv):
        '''
        PARAMETER: csv- csv file with coresponding folder source and destination data of folders that will be backed up -> is passed into the function "read_csv" before executed
        '''
        start = time.perf_counter()
        csvd = read_csv(csv)
        data = csvd[0]
        num = csvd[1]
        now = datetime.now()
        print(f'{now}: Starting import...')
        count = -1
        for i in range(num):
            count = count + 1
            item = data[count]
            #check if exists
            if dircheck(item.src) and dircheck(item.dest):
                #compare modification times 
                src_mod = lastmod_s(item.src)
                dest_mod = lastmod_s(item.dest)
                if src_mod <= dest_mod:
                    print(f'{now}: [{item.name}] was passed\n')
                elif src_mod > dest_mod:  
                    print(f'{now}: Saving [{item.name}]...')
                    #test if dest already exist -> if so replace, if not, just copy
                    if os.path.exists(item.dest) == True:
                        print(f'{now}: Removing existing files...')
                        shutil.rmtree(item.dest)
                        shutil.copytree(item.src, item.dest)
                        print(f'{now}: Saved [{item.name}]\n')
                    else:
                        print(f'{now}: Saving [{item.name}]...')
                        shutil.copytree(item.src, item.dest)
                        print(f'{now}: Saved [{item.name}]\n')
                else:
                    print(f'{now}: Error occured while comparing mod times for [{item.name}]')
            elif not dircheck(item.src) and dircheck(item.dest):
                print(f'{now}: Source directory for [{item.name}] does not exist')
            elif dircheck(item.src) and not dircheck(item.dest):
                print(f'{now}: Destination directory for [{item.name}] does not exist')
            elif not dircheck(item.src) and not dircheck(item.dest):
                print(f'{now}: Neither source nor destination directories for [{item.name}] exist')
            else:
                print(f'{now}: Error occured while testing source and destination locations for [{item.name}]')
        finish = time.perf_counter()
        print(f'{now}: Process completed successfully in {round(finish-start, 2)} seconds')
        return now


def addbackup(name, src, dest, inpfile=default_csv):
    '''
    PARAMETER: inpfile- the file that will be written to 

    PARAMETER: name- the name of the game that the corresponding data will be for

    PARAMETER: src- source path where game saves are saved

    PARAMETER: dest- the destination folder where saves will be copied to 
    '''
    output = pd.DataFrame([[name, src, dest]])
    output.to_csv(inpfile, header = False, index = False, mode = 'a')


def read_csv(file:str=default_csv):
    '''
    Reads csv file from path passed into it and returns a dictionary with keys representing rows, and their defentions storing a "gamedata" data type. Also returns the number of rows for convenience.
    '''
    path = str(file)
    #read csv with pandas
    data = pd.read_csv(path)
    #read csv with csv.reader to get number of rows of data
    readfile = open(path, 'r+')
    read = csv.reader(readfile)
    #takes into account a label column at the top of the file
    rows = len(list(read))-1
    row = -1
    #dictionary that is returned
    ret = {}
    for i in range(rows):
        row = row + 1 
        key = row
        #use pandas and the current row to access each item of data on a row and place it into a dictionary with a key corresponding to that row -> is able to return as many rows as need without returning an unknown amount of variables
        value = backupsync(data.iloc[row, 0], data.iloc[row, 1], data.iloc[row, 2])
        ret[key] = value
    return ret, rows

class backupsync:
    def __init__(self, name, src, dest):
        self.name = name
        self.src = src
        self.dest = dest

syncfolders()