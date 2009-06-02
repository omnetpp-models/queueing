simple Router
{
    @display("i=abstract/router;is=s");
    gates:
        inout port[];
}
//
// TODO auto-generated module
//
network EuropePlayground
{
    @display("bgb=6000,4500;bgi=maps/europe,s;bgg=1000,2,grey95;bgs=0.075,km");

    submodules:
        router: Router {
        }
        router1: Router {
        }
        router2: Router {
        }
        router3: Router {
        }
        router4: Router {
        }
        router5: Router {
        }
        router6: Router {
        }
    connections:
        router2.port++ <--> router1.port++;
        router.port++ <--> router1.port++;
        router3.port++ <--> router4.port++;
        router3.port++ <--> router.port++;
        router3.port++ <--> router2.port++;
        router1.port++ <--> router4.port++;
        router.port++ <--> router4.port++;
        router2.port++ <--> router6.port++;
        router2.port++ <--> router5.port++;
}
