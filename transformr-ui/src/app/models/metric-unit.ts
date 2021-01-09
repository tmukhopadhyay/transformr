import { Unit } from './unit';

export interface MetricUnit {
  lengths: Array<Unit>;
  areas: Array<Unit>;
  volumes: Array<Unit>;
  masses: Array<Unit>;
  temperatures: Array<Unit>;
}
