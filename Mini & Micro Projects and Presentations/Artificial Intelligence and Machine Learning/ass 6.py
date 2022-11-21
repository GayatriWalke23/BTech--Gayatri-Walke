def unify(numberone_predicate, numbertwo_predicate, numberone_arguments, numbertwo_arguments):
    # Step. 1: If Ψ1 or Ψ2 is a variable or constant, then:
    if len(numberone_predicate) == 1 and len(numbertwo_predicate) == 1:
        # a) If Ψ1 or Ψ2 are identical, then return NIL.
        if numberone_predicate == numbertwo_predicate:
            return "NULL"
        # b) Else if Ψ1is a variable,
        elif not numberone_predicate.isnumeric():
            # a. then if Ψ1 occurs in Ψ2, then return FAILURE
            for i in numbertwo_arguments:
                if i == numberone_predicate:
                    return "UNIFICATION FAILED"
            # b. Else return { (Ψ2/ Ψ1)}.
            else:
                return "{(" + numbertwo_predicate + "/ " + numberone_predicate + ")}"
        # c) Else if Ψ2 is a variable,
        elif not numbertwo_predicate.isnumeric():
            # a. If Ψ2 occurs in Ψ1 then return FAILURE,
            for i in numberone_arguments:
                if i == numbertwo_predicate:
                    return "UNIFICATION FAILED"
            # b. Else return {( Ψ1/ Ψ2)}.
            else:
                return "{(" + numberone_predicate + "/ " + numbertwo_predicate + ")}"
        # d) Else return FAILURE.
        else:
            return "UNIFICATION FAILED"

    # Step.2: If the initial Predicate symbol in Ψ1 and Ψ2 are not same, then return FAILURE.
    if numberone_predicate == numbertwo_predicate:
        return "UNIFICATION FAILED (initial predicate symbol is not same)"

    # Step. 3: IF Ψ1 and Ψ2 have a different number of arguments, then return FAILURE.
    if len(numberone_arguments) != len(numbertwo_arguments):
        return "UNIFICATION FAILED (length of arguments is not same)"
    # Step. 4: Set Substitution set(SUBST) to NIL.
    substitution_set = []
    # Step. 5: For i=1 to the number of elements in Ψ1.
    for i in range(len(numberone_arguments)):
        # a) Call Unify function with the ith element of Ψ1 and ith element of Ψ2, and put the result into S.
        S = unify(numberone_arguments[i], numbertwo_arguments[i], 1, 1)
        # b) If S = failure then returns Failure
        if(S.__contains__("UNIFICATION FAILED")):
            return "UNIFICATION FAILED"
        # c) If S ≠ NIL then do,
        if S != "NULL":
            # a. Apply S to the remainder of both L1 and L2.
            # b. SUBST= APPEND(S, SUBST).
            substitution_set.append(S)
    # Step.6: Return SUBST.
    return substitution_set


if __name__ == '__main__':
    print('\n                      UNIFICATION ALGORITHM                    \n')
    choice = 1
    print(" Enter")
    numberone_predicate = input("         Predicate 1 : ")
    numbertwo_predicate = input("         Predicate 2 : ")
    numberone_args = int(input("         Number of arguments in %s : " % numberone_predicate))
    numbertwo_args = int(input("         Number of arguments in %s : " % numberone_predicate))
    numberone_arguments = []
    numbertwo_arguments = []
    print("         Enter the arguments in %s : " % numberone_predicate)
    for i in range(numberone_args):
        j = input("                 Argument %d : " % i)
        numberone_arguments.append(j)
    for i in range(numbertwo_args):
        j = input("                 Argument %d : " % i)
        numbertwo_arguments.append(j)

    result = unify(numberone_predicate, numbertwo_predicate, numberone_arguments, numbertwo_arguments)

    while choice != 0:

        print("\n \n Do you want to solve another graph unification problem ? ")
        choice = 4
        choice = int(input("Enter 0-No/1-Yes :  "))
        while choice < 0 or choice > 1:
            choice = int(input("            Enter 0-No/1-Yes :  "))

    print('             THANK YOU')
