import sys
from matplotlib import pyplot as plt

points = []
index = {}
file1 = open('map.txt', 'r')
Lines = file1.readlines()
count = 0
n=0
for line in Lines:
    node = line.strip().split(' ')
    if count == 0:
        n=int(node[0])
    elif count <= n:
        points.append([float(node[2]),float(node[1])])
        index[node[0]] = count-1
    else:
        plt.plot([points[index[node[0]]][0] , points[index[node[1]]][0]], [points[index[node[0]]][1] , points[index[node[1]]][1]], 'bo-', linestyle='dashed')  
    count += 1
for i in range(2,len(sys.argv)-1):
    plt.plot([points[index[sys.argv[i]]][0] , points[index[sys.argv[i+1]]][0]], [points[index[sys.argv[i]]][1] , points[index[sys.argv[i+1]]][1]], 'bo-')
    plt.annotate(sys.argv[i], (points[index[sys.argv[i]]][0], points[index[sys.argv[i]]][1]))
    if i == len(sys.argv)-2:
        plt.annotate(sys.argv[i+1], (points[index[sys.argv[i+1]]][0], points[index[sys.argv[i+1]]][1]))

plt.savefig(sys.argv[1]+".png")