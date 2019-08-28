dog_age = input('give me age in dog years')#set dog_age to the value the user enters
try:#Try to cast the input
    f = float(dog_age)#to see if user inputs a number
    if float(dog_age) > 0:#to see if user inputs a positive number
        dog_age = float(dog_age)#cast dog_age into a float
        if dog_age <= 1:
            human_age = round(15 * dog_age,2)
            print('The given dog age',dog_age,'is',human_age, 'in human years.')#For the first year, one dog year is equal to 15 human years
        elif 1 < dog_age <= 2:
            human_age = round(12 * dog_age,2)
            print('The given dog age', dog_age, 'is', human_age, 'in human years.')#For the first 2 years, each dog year is equal to 12 human years
        elif 2 < dog_age <= 3:
            human_age = round(9.3 * dog_age,2)
            print('The given dog age', dog_age, 'is', human_age, 'in human years.')#For the first 3 years, each dog year is equal to 9.3 human years
        elif 3 < dog_age <= 4:
            human_age = round(8 * dog_age,2)
            print('The given dog age', dog_age, 'is', human_age, 'in human years.')#For the first 4 years, each dog year is equal to 8 human years
        elif 4 < dog_age <= 5:
            human_age = round(7.2 * dog_age,2)
            print('The given dog age', dog_age, 'is', human_age, 'in human years.')#For the first 5 years, each dog year is equal to 7.2 human years
        elif dog_age > 5:
            human_age = round(36 + 7 * (dog_age - 5),2)
            print('The given dog age', dog_age, 'is', human_age, 'in human years.')#After 5 years, each dog year is equal to 7 human years.
    else:
        print('Age must be a positive number')#if dog_age is negative, print a warning
except ValueError as e:
    print(dog_age,'is not a valid age')#if the user doesn’t input a number,print a warning