
void bar( int q) do
  for(;q > 0;) do
    q--;
    for(var i = 0; i < q; i++) do
    var x = 5;
    var y = 5;
    if((q % 4) % 3 == 0) then
      x = -5;
    end
    if(q % 4 < 2) then
      y = -5;
    end 
    walk(x, y);
    moveCam(x, y);
    frameAdvance 2;
    end
    frameAdvance 1;
  end
  frameAdvance 5;
  return ;
end

println;
print "hi";
bar(5);
print " goodbye";