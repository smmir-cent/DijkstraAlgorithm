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
        plt.plot([points[index[node[0]]][0] , points[index[node[1]]][0]], [points[index[node[0]]][1] , points[index[node[1]]][1]], 'bo-')
        plt.annotate(node[0], (points[index[node[0]]][0], points[index[node[0]]][1]))
        plt.annotate(node[1], (points[index[node[1]]][0], points[index[node[1]]][1]))
    count += 1
plt.savefig("Graph.png")