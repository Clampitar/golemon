fun foo(i) do
  return 2;
end

fun bar(x) do
  return foo(3);
end

print bar(4) + 2;
println;

print "Autre resultat: ";
print foo(10) - 1;
println;

# ERREUR D'.é..: .....
#  Dans foo
#  Appelé par bac ligne x position y
#  Appelé par ...
#  Appelé par programme principal ligne x position y