public class Node {

    //checks if current node is the root
    public boolean isRoot;
    public boolean isExt;
    //8 octants le = left, ri = right, up = up, do = down, ba = back, fr = front
    private Node leUpFr, riUpFr, leDoFr, riDoFr, leUpBa, riUpBa, leDoBa, riDoBa;
    //if body != null, external node
    private CelestialBody body;
    //linked list to previous node to update all center of masses and mass sums
    private Node previous;
    //class that checks for the boundries of this node
    private Range NRange;
    //centerofmass and totalmass of all bodies within this sector
    private double totalMass;
    private Vector3 centerofmass;
    //theta changeable here
    private double theta = 1;

    //creates new node that is marked as root for octree
    public Node(CelestialBody body, double width, double height, double depth) {               //root
        this.body = body;
        this.NRange = new Range(width, height, depth);
        this.isRoot = true;
        this.isExt = true;
        this.totalMass = body.mass;
        this.centerofmass = body.position;
    }

    //creates new subnode that has a parent node as previous
    public Node(Node oldNode, CelestialBody body, int d) {                                    //subnodes
        this.body = body;
        this.NRange = new Range(oldNode.NRange, d);
        this.isRoot = false;
        this.totalMass = body.mass;
        this.centerofmass = body.position;
        this.previous = oldNode;
        this.isExt = true;
    }


    //insert into tree
    public void insert(CelestialBody body) {

        if ((this.body != null) && (this.isRoot)) {            //if root has already a body, insert the rootbody into subnodes, then insert "body"
            CelestialBody bb = this.body;
            this.body = null;
            insert(bb);
        }

        //switch for which octant the body is in, with the method "isInRange" from the class Range
        // which returns the number of the octant to insert
        switch (NRange.isInRange(body.position.getX(), body.position.getY(), body.position.getZ())) {
            case 1:
                if (leUpFr == null) {                                                         //if subnode free, insert
                    this.leUpFr = new Node(this, body, 1);
                    this.isExt = false;

                } else {
                    if (leUpFr.body != null) {                                                //was external node, split
                        CelestialBody bb = this.leUpFr.body;
                        this.leUpFr.body = null;                                              //the actual node becomes an internal node
                        this.leUpFr.isExt = false;
                        this.leUpFr.insert(bb);                                               //insert old body into subnode of this node first
                        this.leUpFr.insert(body);

                    } else {                                                                  //was internal node, insert into subnode
                        this.leUpFr.insert(body);
                    }
                }
                update(this, body);                                                  //update center of mass, and total masses
                break;
            case 2:
                if (riUpFr == null) {
                    this.riUpFr = new Node(this, body, 2);
                    this.isExt = false;

                } else {
                    if (riUpFr.body != null) {
                        CelestialBody bb = this.riUpFr.body;
                        this.riUpFr.body = null;
                        this.riUpFr.isExt = false;
                        this.riUpFr.insert(bb);
                        this.riUpFr.insert(body);

                    } else {
                        this.riUpFr.insert(body);
                    }
                }
                update(this, body);
                break;
            case 3:
                if (leDoFr == null) {
                    this.leDoFr = new Node(this, body, 3);
                    this.isExt = false;
                } else {
                    if (leDoFr.body != null) {
                        CelestialBody bb = this.leDoFr.body;
                        this.leDoFr.body = null;
                        this.leDoFr.isExt = false;
                        this.leDoFr.insert(bb);
                        this.leDoFr.insert(body);

                    } else {
                        this.leDoFr.insert(body);
                    }
                }
                update(this, body);
                break;
            case 4:
                if (riDoFr == null) {
                    this.riDoFr = new Node(this, body, 4);
                    this.isExt = false;
                } else {
                    if (riDoFr.body != null) {
                        CelestialBody bb = this.riDoFr.body;
                        this.riDoFr.body = null;
                        this.riDoFr.isExt = false;
                        this.riDoFr.insert(bb);
                        this.riDoFr.insert(body);
                    } else {
                        this.riDoFr.insert(body);
                    }
                }
                update(this, body);
                break;
            case 5:
                if (leUpBa == null) {
                    this.leUpBa = new Node(this, body, 5);
                    this.isExt = false;
                } else {
                    if (leUpBa.body != null) {
                        CelestialBody bb = this.leUpBa.body;
                        this.leUpBa.body = null;
                        this.leUpBa.isExt = false;
                        this.leUpBa.insert(bb);
                        this.leUpBa.insert(body);
                    } else {
                        this.leUpBa.insert(body);
                    }
                }
                update(this, body);
                break;
            case 6:
                if (riUpBa == null) {
                    this.riUpBa = new Node(this, body, 6);
                    this.isExt = false;
                } else {
                    if (riUpBa.body != null) {
                        CelestialBody bb = this.riUpBa.body;
                        this.riUpBa.body = null;
                        this.riUpBa.isExt = false;
                        this.riUpBa.insert(bb);
                        this.riUpBa.insert(body);
                    } else {
                        this.riUpBa.insert(body);
                    }
                }
                update(this, body);
                break;
            case 7:
                if (leDoBa == null) {
                    this.leDoBa = new Node(this, body, 7);
                    this.isExt = false;
                } else {
                    if (leDoBa.body != null) {
                        CelestialBody bb = this.leDoBa.body;
                        this.leDoBa.body = null;
                        this.leDoBa.isExt = false;
                        this.leDoBa.insert(bb);
                        this.leDoBa.insert(body);
                    } else {
                        this.leDoBa.insert(body);
                    }
                }
                update(this, body);
                break;
            case 8:
                if (riDoBa == null) {
                    this.riDoBa = new Node(this, body, 8);
                    this.isExt = false;
                } else {
                    if (riDoBa.body != null) {
                        CelestialBody bb = this.riDoBa.body;
                        this.riDoBa.body = null;
                        this.riDoBa.isExt = false;
                        this.riDoBa.insert(bb);
                        this.riDoBa.insert(body);
                    } else {
                        this.riDoBa.insert(body);
                    }
                }
                update(this, body);
                break;
            default:
                System.out.println("Error: unhandled partition " + body.position.toString());       //if out of range, just break and don't insert anything
        }
    }


    //update of all Center of Mass and Mass sum
    private void update(Node actNode, CelestialBody body) {
        while (!actNode.isRoot) {
            actNode.totalMass += body.mass;
            actNode.centerofmass = Vector3.comCal(actNode.centerofmass, actNode.totalMass, body.position, body.mass);
            actNode = actNode.previous;
        }
        if (actNode.isRoot) {
            actNode.totalMass += body.mass;
            actNode.centerofmass = Vector3.comCal(actNode.centerofmass, actNode.totalMass, body.position, body.mass);
        }
    }

    // calculates the force exerted on a body from all other bodies in the tree, depending on theta either an eexternal node (one body)
    // or an internal node (multiple bodies cumulated) is consulted for the calculation
    public Vector3 force(CelestialBody bodyToMove, Vector3 totalForce) {
        if (this.body != null) {                                                                        //external node?
            if (this.body != bodyToMove) {                                                              //check if same body
                totalForce = totalForce.plus(gravitationalForce(bodyToMove, this.centerofmass, this.totalMass));
                return totalForce;
            }
        //internal node -> check if r/d > theta -> if yes caculate grav. force with com & total mass of the Octant
        } else {
            if (bodyToMove.position.distanceTo(this.NRange.getMp()) / (this.NRange.getWidth()) > theta ) {
                totalForce = totalForce.plus(gravitationalForce(bodyToMove, this.NRange.getMp(), this.totalMass));
                return totalForce;
            //if r/d <theta -> recursive call of every subbnode
            } else {
                if (leUpFr != null) {
                    totalForce = totalForce.plus(leUpFr.force(bodyToMove, totalForce));
                }
                if (riUpFr != null) {
                    totalForce = totalForce.plus(riUpFr.force(bodyToMove, totalForce));
                }
                if (leDoFr != null) {
                    totalForce = totalForce.plus(leDoFr.force(bodyToMove, totalForce));
                }
                if (riDoFr != null) {
                    totalForce = totalForce.plus(riDoFr.force(bodyToMove, totalForce));
                }
                if (leUpBa != null) {
                    totalForce = totalForce.plus(leUpBa.force(bodyToMove, totalForce));
                }
                if (riUpBa != null) {
                    totalForce = totalForce.plus(riUpBa.force(bodyToMove, totalForce));
                }
                if (leDoBa != null) {
                    totalForce = totalForce.plus(leDoBa.force(bodyToMove, totalForce));
                }
                if (riDoBa != null) {
                    totalForce = totalForce.plus(riDoBa.force(bodyToMove, totalForce));
                }
                return totalForce;
            }
        }
        return totalForce;
    }

    //removes all bodys from the tree
    public void remove() {
        if (leUpFr != null) {
            if (leUpFr.isExt) {
                leUpFr = null;
            } else {
                leUpFr.remove();
            }
        }
        if (riUpFr != null) {
            if (riUpFr.isExt) {
                riUpFr = null;
            } else {
                riUpFr.remove();
            }
        }
        if (leDoFr != null) {
            if (leDoFr.isExt) {
                leDoFr = null;
            } else {
                leDoFr.remove();
            }
        }
        if (riDoFr != null) {
            if (riDoFr.isExt) {

                riDoFr = null;
            } else {
                riDoFr.remove();
            }
        }
        if (leUpBa != null) {
            if (leUpBa.isExt) {
                leUpBa = null;
            } else {
                leUpBa.remove();
            }
        }
        if (riUpBa != null) {
            if (riUpBa.isExt) {
                riUpBa = null;
            } else {
                riUpBa.remove();
            }
        }
        if (leDoBa != null) {
            if (leDoBa.isExt) {
                leDoBa = null;
            } else {
                leDoBa.remove();
            }
        }
        if (riDoBa != null) {
            if (riDoBa.isExt) {
                riDoBa = null;
            } else {
                riDoBa.remove();
            }
        }
    }

    public Vector3 gravitationalForce(CelestialBody bodyToMove, Vector3 pos, double mass) {
        Vector3 direction = pos.minus(bodyToMove.position);
        double r = direction.length();
        direction.normalize();
        double force = Simulation.G * bodyToMove.mass * mass / (r * r);
        return direction.times(force);
    }
}




