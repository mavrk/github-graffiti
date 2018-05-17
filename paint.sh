#!/bin/bash		
while read line	 
do		
	IFS='/' read -ra PARAMS <<< "$line"
	D=${PARAMS[0]}
	M=${PARAMS[1]}
	Y=${PARAMS[2]}
	I=${PARAMS[3]}
	if [ ! -d "$Y" ]; then
    	mkdir $Y
	fi	
		cd $Y
		if [ ! -d "$M" ]; then
			mkdir $M
		fi
			cd $M
			if [ ! -d "$D" ]; then
				mkdir $D
			fi
				cd $D
				for i in $( eval echo {1..$I} )
      			do
      				echo "$i on $D/$M/$Y" > commit.md
        			export GIT_COMMITTER_DATE="$Y-$M-$D 12:$i:00"
        			export GIT_AUTHOR_DATE="$Y-$M-$D 12:$i:00"
        			git add commit.md -f
        			git commit --date="$Y-$M-$D 12:0$i:00" -m "$i on $M $D $Y"
        		done
        	cd ../
        cd ../
    cd ../	
done < dates.txt
git push origin master
git rm -rf 20**
git commit -am "cleanup"
git push origin master