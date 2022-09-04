//GAYATRI WALKE (ROLL NO : 2466)

#menu driven program to mount-unmount device


$choice		#declare variables
$path
$directory
choice=1
lsblk
read -p "enter where the device is inserted : " path  #take path from user
cd ..
echo "The contents of this directory are : "
ls
read -p "enter the directory where the device is to be mounted : " directory  #take path from user
cd $directory
echo "Now you are in :  "
pwd
directory=`pwd` 
until [ $choice -eq 0 ]
do
echo "    MENU "			#display menu
echo "1. MOUNT"
echo "2. UNMOUNT"
echo "3. EJECT"
echo "0. EXIT"
echo " "

read -p "enter your choice : " choice  #take user choice

case $choice in
	1) #mount
	mount $path $directory
	cd $directory
	echo "The contents of this directory are : "
	ls                                                      
	echo "The device is mounted successfully!"
	;;
	2) #unmount
	cd ..
	umount $directory
	echo "The device is unmounted successfully!"
	;;
	3) #eject
	eject $path
	echo " EJECTED "
	;;
	0) #exit
	echo " THANK YOU "
	;;
esac  #close switch
done	#close case
