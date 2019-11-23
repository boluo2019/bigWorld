
import json

def resolveJson(path):
    file = open(path, "r")
    fileJson = json.loads(file)
    return fileJson


path = "/home/hei/work/0-source/github/bigWorld/yoga/YogaAllianceInChina.json"
result = resolveJson(path)
print(result)
