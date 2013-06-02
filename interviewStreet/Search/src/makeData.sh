file="./data"
minimumsize=90000
actualsize=$(du -b "$file" | cut -f 1)

while [ $actualsize -le 5368709120 ]
do
    ./loggen 100000000000000 >> data
done
