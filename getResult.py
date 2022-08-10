import os
from re import findall
from matplotlib import pyplot as plt
os.chdir(os.path.abspath(os.path.dirname(__file__)))#解析进入程序所在目录
EXIT_SUCCESS = 0
EXIT_FAILURE = 1
EOF = -1
legend = ["EKGen", "DKGen", "Enc", "Dec"]


def drawFromFile(filepath, encoding = "utf-8"):
	with open (filepath, "r", encoding = encoding) as f:
		content = f.read()
	index = 0
	array_lists = [[], [], [], []]
	for line in content.split("\n"):
		if "Run Time" in line:
			matches = findall("\\d+", line)
			if matches:
				array_lists[index].append(int(matches[0]))
				index = (index + 1) % 4
	x = [10, 20, 30, 40, 50, 60]
	print(array_lists)
	for i in range(4):
		if sorted(array_lists[i][:len(x)]) != array_lists[i][:len(x)]:
			print(legend[i], array_lists[i][:len(x)])
		plt.plot(x, array_lists[i][:len(x)], marker = "x")
	plt.legend(legend)
	plt.show()
	plt.close()

def main():
	drawFromFile("results.txt")
	return EXIT_SUCCESS



if __name__ == "__main__":
	exit(main())