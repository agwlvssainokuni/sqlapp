#!/bin/bash

cd $(dirname ${BASH_SOURCE[0]})

count=0
for file in $*
do
	count=$((count+1))
	props="${props} -PdefinitionFile.${count}=${file}"
done

gradle generateForm ${props}
